package MySQL;

import JavaBeans.Order;
import JavaBeans.Product;
import JavaBeans.UserAccount;
import Utils.DateUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductTable {

    private static final String FIND_ALL_PRODUCTS = "SELECT * FROM products ORDER BY roomNo";
    private static final String FIND_SUITABLE = "SELECT * FROM products " +
            "LEFT JOIN orders ON products.roomNo = orders.product_id " +
            "WHERE sleeps =?";
    private static final String FIND_BY_ID = "SELECT * FROM products " +
            "LEFT JOIN orders ON products.roomNo = orders.product_id " +
            "WHERE roomNo=?";
    private static final String FIND_PRODUCTS_ORDERS = "SELECT * FROM products " +
            "LEFT JOIN orders ON products.roomNo = orders.product_id ";
    private static final String MARK_AS_TAKEN = "UPDATE products SET isTaken=1 where roomNo = ?";
    private static final String MARK_AS_FREE = "UPDATE products SET isTaken=0 where roomNo = ?";

    public static List<Product> extractAll() {
        List<Product> productList = new ArrayList<>();
        try (Connection conn = ConnectionUtils.getConnection()) {
            Statement prst = conn.createStatement();
            ResultSet rs = prst.executeQuery(FIND_ALL_PRODUCTS);
            while (rs.next()) {
                Product product = new Product();
                product.setRoomNo(Integer.parseInt(rs.getString("roomNo")));
                product.setPrice(Double.parseDouble(rs.getString("price")));
                product.setSleeps(Integer.parseInt(rs.getString("sleeps")));
                product.setImage((rs.getString("image")));
                product.setTaken(rs.getBoolean("isTaken"));
                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    public static List<Product> selectSuitable(int sleeps, String checkIn, String checkOut) {
        List<Product> productList = null;
        try (Connection conn = ConnectionUtils.getConnection()) {
            productList = new ArrayList<>();
            PreparedStatement preparedStatement = conn.prepareStatement(FIND_SUITABLE);
            preparedStatement.setInt(1, sleeps);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String startDate1 = rs.getString("checkIn");
                String endDate1 = rs.getString("checkOut");
                if ((startDate1 == null || endDate1 == null) || !DateUtils.datesOverlap(startDate1, endDate1, checkIn, checkOut)) {
                    Product product = new Product();
                    product.setRoomNo(rs.getInt("roomNo"));
                    product.setImage(rs.getString("image"));
                    product.setSleeps(rs.getInt("sleeps"));
                    product.setPrice(Double.parseDouble(rs.getString("price")));
                    productList.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public static boolean isTakenById(int id, String startDate, String endDate) {
        try (Connection conn = ConnectionUtils.getConnection()) {
            PreparedStatement prst = conn.prepareStatement(FIND_BY_ID);
            prst.setInt(1, id);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                String checkIn = rs.getString("checkIn");
                String checkOut = rs.getString("checkOut");

                if (checkIn == null || checkOut == null) {
                    return false;
                }
                if (DateUtils.datesOverlap(startDate, endDate, checkIn, checkOut)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean setTakenOrFree(int roomId, int i) {
        try (Connection conn = ConnectionUtils.getConnection()) {
            PreparedStatement prst = conn.prepareStatement(i == 0 ? MARK_AS_FREE : MARK_AS_TAKEN);
            prst.setInt(1, roomId);
            prst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean setFree() {
        try (Connection conn = ConnectionUtils.getConnection()) {
            Statement prstm = conn.createStatement();
            ResultSet rs = prstm.executeQuery(FIND_PRODUCTS_ORDERS);
            while (rs.next()) {
                if (rs.getDate("checkIn") == null) {
                    setTakenOrFree(rs.getInt("roomNo"), 0);
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
