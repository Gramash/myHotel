package MySQL;


import JavaBeans.Order;
import JavaBeans.UserAccount;
import Utils.DateUtils;
import com.mysql.jdbc.MysqlDataTruncation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class OrdersTable {

    private static final String INSERT_ORDER = "INSERT INTO orders VALUES (?,?, ?, ?, DEFAULT, DEFAULT )";
    private static final String GET_ORDER_BY_USERID =
            "SELECT products.roomNo, products.sleeps, orders.checkIn, orders.checkOut, products.price, paid, ts, products.class " +
                    "FROM orders " +
                    "INNER JOIN products ON products.roomNo = orders.product_id " +
                    "WHERE user_id = ?";
    private static final String GET_ALL_ORDERS =
            "SELECT products.roomNo, products.sleeps, products.class, orders.checkIn, orders.checkOut, products.price, orders.paid, users.name, users.email, ts " +
                    "FROM orders " +
                    "INNER JOIN products ON products.roomNo = orders.product_id " +
                    "INNER JOIN users ON users.user_id = orders.user_id";
    private static final String UPDATE_BEFORE_QUERY = "DELETE FROM orders WHERE ts < (NOW() - INTERVAL 1 MINUTE ) " +
            "AND paid='0' " +
            "OR checkOut <= curdate()";
    private static final String CONFIRM_ORDER = "UPDATE ORDERS SET paid = 1 WHERE product_id =? AND checkIn=? AND checkOut=?";


    private static void updateBeforeQuery() {
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement prstm = conn.prepareStatement(UPDATE_BEFORE_QUERY);
            prstm.execute();
            ProductTable.setFree();
            conn.commit();
        } catch (Exception e) {
            ConnectionUtils.rollback(conn);
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeCon(conn);
        }
    }

    public static boolean insertOrder(int userId, int productId, String checkIn, String checkOut, String appId) throws ParseException {
        if (DateUtils.isBeforeToday(checkIn)) {
            return false;
        }
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement prstm = conn.prepareStatement(INSERT_ORDER);
            int k = 1;
            prstm.setInt(k++, userId);
            prstm.setInt(k++, productId);
            prstm.setString(k++, checkIn);
            prstm.setString(k, checkOut);
            prstm.execute();
            if (appId != null) {
                ApplicationsTable.closeApplication(appId);
            }
            conn.commit();
            return true;
        } catch (SQLException e) {
            ConnectionUtils.rollback(conn);
            e.printStackTrace();
            return false;
        } finally {
            ConnectionUtils.closeCon(conn);
        }

    }

    public static List<Order> getOrderByUserId(int userId) {
        List<Order> orderList = null;
        Order order;
        PreparedStatement getOrdersByUserId;
        ResultSet rs;
        java.sql.Date date = null;
        try (Connection conn = ConnectionUtils.getConnection()) {
            orderList = new ArrayList<>();
            updateBeforeQuery();
            getOrdersByUserId = conn.prepareStatement(GET_ORDER_BY_USERID);
            int k = 1;
            getOrdersByUserId.setInt(k, userId);
            rs = getOrdersByUserId.executeQuery();
            while (rs.next()) {
                String checkIn = rs.getString("checkIn");
                String checkOut = rs.getString("checkOut");
                float daysCount = DateUtils.countDays(checkIn, checkOut);
                if (!rs.getBoolean("paid")) {
                    date = DateUtils.add2Days(rs.getDate("ts"));
                }
                order = new Order(rs.getInt("roomNo"), rs.getInt("sleeps"),
                        rs.getDate("checkIn"), rs.getDate("checkOut"),
                        (rs.getDouble("price") * daysCount), date, rs.getString("class"));
                orderList.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;
    }

    public static List<Order> getOrderManager() {
        List<Order> orderList = null;
        Order order;
        PreparedStatement getOrdersByUserId;
        ResultSet rs;
        java.sql.Date date = null;
        Connection conn = null;
        try  {
            conn = ConnectionUtils.getConnection();
            orderList = new ArrayList<>();
            updateBeforeQuery();
            getOrdersByUserId = conn.prepareStatement(GET_ALL_ORDERS);
            rs = getOrdersByUserId.executeQuery();
            while (rs.next()) {
                if (!rs.getBoolean("paid")) {
                    date = DateUtils.add2Days(rs.getDate("ts"));
                }
                UserAccount user = UserTable.findByEmail(rs.getString("email"));
                order = new Order(rs.getInt("roomNo"), rs.getInt("sleeps"),
                        rs.getDate("checkIn"), rs.getDate("checkOut"),
                        Double.parseDouble(rs.getString("price")), date, rs.getString("class"), user);
                orderList.add(order);
            }
            conn.commit();
        } catch (Exception e) {
            ConnectionUtils.rollback(conn);
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeCon(conn);
        }
        return orderList;
    }

    public static boolean confirmOrder(int productId, java.sql.Date checkIn, java.sql.Date checkOut) {
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();
            PreparedStatement prstm = conn.prepareStatement(CONFIRM_ORDER);
            int k = 1;
            prstm.setInt(k++, productId);
            prstm.setDate(k++, checkIn);
            prstm.setDate(k, checkOut);
            prstm.executeUpdate();
            conn.commit();
            return true;
        } catch (SQLException e) {
            ConnectionUtils.rollback(conn);
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeCon(conn);
        }
        return false;
    }


}
