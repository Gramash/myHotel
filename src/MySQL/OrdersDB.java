package MySQL;


import JavaBeans.Order;
import JavaBeans.UserAccount;
import Utils.UserUtils;
import Wrappers.UserRoleRequestWrapper;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OrdersDB {

    private static final String INSERT_ORDER = "insert into orders values (DEFAULT, ?,?, ?, ?, DEFAULT )";
    private static final String GET_ORDER_BY_USERID =
            "select products.roomNo, products.sleeps, orders.checkIn, orders.checkOut, products.price " +
                    "from orders " +
                    "inner join products ON products.roomNo = orders.product_id " +
                    "where user_id = ?";
    private static final String GET_ORDER_MANAGER =
            "select products.roomNo, products.sleeps, orders.checkIn, orders.checkOut, products.price, orders.paid, users.name, users.email\n" +
                    "from orders \n" +
                    "inner join products ON products.roomNo = orders.product_id\n" +
                    "inner join users ON users.user_id = orders.user_id";
    private static final String UPDATE_BEFORE_QUERY = "DELETE FROM orders WHERE ts < (NOW() - INTERVAL 1 DAY) AND paid='0'";

    private static void updateBeforeQuery (){
        try (Connection conn = ConnectionUtils.getConnection()) {
            PreparedStatement prstm = conn.prepareStatement(UPDATE_BEFORE_QUERY);
            prstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean insertOrder(int userId, int productId, String checkIn, String checkOut) {
        try (Connection conn = ConnectionUtils.getConnection()) {
            PreparedStatement prstm = conn.prepareStatement(INSERT_ORDER);
            int k = 1;
            prstm.setInt(k++, userId);
            prstm.setInt(k++, productId);
            prstm.setString(k++, checkIn);
            prstm.setString(k, checkOut);
            prstm.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Order> getOrderByUserId(int userId) {
        List<Order> orderList = null;
        Order order;
        PreparedStatement getOrdersByUserId;
        ResultSet rs;
        try (Connection conn = ConnectionUtils.getConnection()) {
            orderList = new ArrayList<>();
            updateBeforeQuery();
            getOrdersByUserId = conn.prepareStatement(GET_ORDER_BY_USERID);
            int k = 1;
            getOrdersByUserId.setInt(k, userId);
            rs = getOrdersByUserId.executeQuery();
            while (rs.next()) {
                order = new Order(rs.getInt("roomNo"), rs.getInt("sleeps"),
                       rs.getDate("checkIn"), rs.getDate("checkOut"), rs.getDouble("price"));
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
        try (Connection conn = ConnectionUtils.getConnection()) {
            orderList = new ArrayList<>();
            updateBeforeQuery();
            getOrdersByUserId = conn.prepareStatement(GET_ORDER_MANAGER);
            rs = getOrdersByUserId.executeQuery();
            while (rs.next()) {
                UserAccount user = UserDB.findByEmail(rs.getString("email"));
                order = new Order(rs.getInt("roomNo"), rs.getInt("sleeps"),
                        rs.getDate("checkIn"), rs.getDate("checkOut"), Double.parseDouble(rs.getString("price")),
                        rs.getBoolean("paid"), user);
                orderList.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;
    }

}
