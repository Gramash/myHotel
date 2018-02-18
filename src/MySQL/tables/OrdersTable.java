package MySQL.tables;


import MySQL.ConnectionUtils;
import MySQL.Fields;
import MySQL.JavaBeans.Order;
import MySQL.JavaBeans.UserAccount;
import Utils.DateUtils;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class OrdersTable {

    private static final String INSERT_ORDER = "INSERT INTO orders VALUES (?,?, ?, ?, ?, DEFAULT )";
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
    private static final String UPDATE_BEFORE_QUERY = "DELETE FROM orders WHERE ts < (NOW() - INTERVAL 3 MINUTE ) " +
            "AND paid='0' " +
            "OR checkOut <= curdate()";
    private static final String CONFIRM_ORDER = "UPDATE ORDERS SET paid = 1 WHERE product_id =? AND checkIn=? AND checkOut=?";
    private static final String GET_ORDERS_PRICE_BY_USER_ID = "SELECT sum(products.price) as aTotal , users.name, users.user_id " +
            "                    FROM orders \n" +
            "                    INNER JOIN users ON users.user_id = orders.user_id  " +
            "                    INNER JOIN products ON products.roomNo = orders.product_id " +
            "                    GROUP BY users.user_id";

    public static List<Order> getOrdersPrice() {
        Connection conn = null;
        List<Order> orderList = null;
        try {
            conn = ConnectionUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(GET_ORDERS_PRICE_BY_USER_ID);
            ResultSet rs = preparedStatement.executeQuery();
            orderList = new ArrayList<>();
            while (rs.next()){
                Order order = new Order();
                UserAccount user = UserTable.findById(rs.getInt("user_id"));
                order.setUser(user);
                order.setUserId(user.getUserID());
                order.setPrice(Double.parseDouble(rs.getString("aTotal")));
                orderList.add(order);
            }
            conn.commit();
        } catch (SQLException e) {
            ConnectionUtils.rollback(conn);
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeCon(conn);

        }
        return orderList;
    }


    private static void updateBeforeQuery() {
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement prstm = conn.prepareStatement(UPDATE_BEFORE_QUERY);
            prstm.execute();
            ProductTable.setFree();
            conn.commit();
        } catch (SQLException e) {
            ConnectionUtils.rollback(conn);
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeCon(conn);
        }
    }

    public static boolean insertOrder(int userId, int productId, String checkIn, String checkOut, String appId, boolean paidUp) throws ParseException {
        if (DateUtils.isBeforeToday(DateUtils.dateToString(
                DateUtils.addDays(DateUtils.stringToDate(checkIn), paidUp ? 0 : -2)))) {
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
            prstm.setString(k++, checkOut);
            prstm.setInt(k, paidUp ? 1 : 0);
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
        try (Connection conn = ConnectionUtils.getConnection()) {
            orderList = new ArrayList<>();
            getOrdersByUserId = conn.prepareStatement(GET_ORDER_BY_USERID);
            int k = 1;
            getOrdersByUserId.setInt(k, userId);
            rs = getOrdersByUserId.executeQuery();
            while (rs.next()) {
                Date date = null;
                String checkIn = rs.getString(Fields.CHECK_IN);
                String checkOut = rs.getString(Fields.CHECK_OUT);
                float daysCount = DateUtils.countDays(checkIn, checkOut);
                if (!rs.getBoolean(Fields.PAID)) {
                    date = DateUtils.addDays(rs.getDate(Fields.TS), 2);
                }
                order = new Order(rs.getInt(Fields.ROOM_NO), rs.getInt(Fields.SLEEPS),
                        rs.getDate(Fields.CHECK_IN), rs.getDate(Fields.CHECK_OUT),
                        (rs.getDouble(Fields.PRICE) * daysCount), date, rs.getString(Fields.CLASS));
                orderList.add(order);
            }
            updateBeforeQuery();
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
        Date date = null;
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();
            orderList = new ArrayList<>();
            updateBeforeQuery();
            getOrdersByUserId = conn.prepareStatement(GET_ALL_ORDERS);
            rs = getOrdersByUserId.executeQuery();
            while (rs.next()) {
                if (!rs.getBoolean(Fields.PAID)) {
                    date = DateUtils.addDays(rs.getDate(Fields.TS), 2);
                }
                UserAccount user = UserTable.findByEmail(rs.getString(Fields.EMAIL));
                order = new Order(rs.getInt(Fields.ROOM_NO), rs.getInt(Fields.SLEEPS),
                        rs.getDate(Fields.CHECK_IN), rs.getDate(Fields.CHECK_OUT),
                        Double.parseDouble(rs.getString(Fields.PRICE)), date, rs.getString(Fields.CLASS), user);
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
