package MySQL;

import JavaBeans.Product;
import Utils.DateUtils;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ProductTable {

    private static final String FIND_ALL_PRODUCTS = "SELECT * FROM products ORDER BY ";
    private static final String FIND_SUITABLE = "SELECT * FROM products " +
            "LEFT JOIN orders ON products.roomNo = orders.product_id " +
            "WHERE sleeps =? AND class=? AND available = 1";
    private static final String FIND_BY_ID = "SELECT * FROM products " +
            "LEFT JOIN orders ON products.roomNo = orders.product_id " +
            "WHERE roomNo=?";
    private static final String FIND_PRODUCTS_ORDERS = "SELECT * FROM products " +
            "LEFT JOIN orders ON products.roomNo = orders.product_id ";
    private static final String MARK_AS_TAKEN = "UPDATE products SET isTaken=1 where roomNo = ?";
    private static final String MARK_AS_FREE = "UPDATE products SET isTaken=0 where roomNo = ?";
    private static final String CHANGE_AVAILABILITY = "UPDATE products SET available=? WHERE roomNo =?";
    private static final String UPDATE_PROD = "UPDATE products SET sleeps=?, price=?, available=?, class=? WHERE roomNo = ? ";

    public static List<Product> extractAll(boolean manager, String orderBy, String order) {
        List<Product> productList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement prst = conn.prepareStatement(FIND_ALL_PRODUCTS + orderBy + " " + order);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                if (!manager && !rs.getBoolean("available")) {
                    continue;
                }
                Product product = new Product();
                product.setClazz(rs.getString("class"));
                product.setAvailable(rs.getBoolean("available"));
                product.setRoomNo(Integer.parseInt(rs.getString("roomNo")));
                product.setPrice(Double.parseDouble(rs.getString("price")));
                product.setSleeps(Integer.parseInt(rs.getString("sleeps")));
                product.setImage((rs.getString("image")));
                product.setTaken(rs.getBoolean("isTaken"));
                productList.add(product);
                conn.commit();
            }
        } catch (Exception e) {
            ConnectionUtils.rollback(conn);
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeCon(conn);
        }
        return productList;
    }

    public static List<Product> selectSuitable(int sleeps, String checkIn, String checkOut, String clazz) throws ParseException {
        List<Product> productList = null;
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();
            conn.setAutoCommit(false);
            productList = new ArrayList<>();
            PreparedStatement preparedStatement = conn.prepareStatement(FIND_SUITABLE);
            int k = 1;
            preparedStatement.setInt(k++, sleeps);
            preparedStatement.setString(k, clazz);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String startDate1 = rs.getString("checkIn");
                String endDate1 = rs.getString("checkOut");
                if ((startDate1 == null || endDate1 == null) || !DateUtils.datesOverlap(startDate1, endDate1, checkIn, checkOut)) {
                    Product product = new Product();
                    product.setClazz(rs.getString("class"));
                    product.setRoomNo(rs.getInt("roomNo"));
                    product.setImage(rs.getString("image"));
                    product.setSleeps(rs.getInt("sleeps"));
                    product.setPrice(Double.parseDouble(rs.getString("price")));
                    productList.add(product);
                }
            }
            conn.commit();
        } catch (SQLException e) {
            ConnectionUtils.rollback(conn);
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeCon(conn);
        }
        return productList;
    }

    public static boolean isTakenById(int id, String startDate, String endDate) throws ParseException {
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();
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
            conn.commit();
        } catch (SQLException e) {
            ConnectionUtils.rollback(conn);
            e.printStackTrace();
        }
        ConnectionUtils.closeCon(conn);
        return false;
    }

    public static void setTakenOrFree(int roomId, int i) {
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();
            conn.setAutoCommit(false);
            String query = (i == 0 ? MARK_AS_FREE : MARK_AS_TAKEN);
            PreparedStatement prst = conn.prepareStatement(query);
            prst.setInt(1, roomId);
            prst.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            ConnectionUtils.rollback(conn);
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeCon(conn);
        }
    }

    public static boolean setFree() {
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();
            conn.setAutoCommit(false);
            Statement prstm = conn.createStatement();
            ResultSet rs = prstm.executeQuery(FIND_PRODUCTS_ORDERS);
            while (rs.next()) {
                if (rs.getDate("checkIn") == null) {
                    setTakenOrFree(rs.getInt("roomNo"), 0);
                }
            }
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


    public static boolean updateProduct(int roomNo, int sleeps, double price, boolean available, String clazz) {
        Connection conn = null;
        try  {
            conn = ConnectionUtils.getConnection();
            PreparedStatement prstm = conn.prepareStatement(UPDATE_PROD);
            int k = 1;
            prstm.setInt(k++, sleeps);
            prstm.setDouble(k++, price);
            prstm.setBoolean(k++, available);
            prstm.setString(k++, clazz);
            prstm.setInt(k, roomNo);
            prstm.executeUpdate();
            conn.commit();
            System.out.println("commited");
            return true;
        } catch (SQLException e) {
            ConnectionUtils.rollback(conn);
            System.out.println("cant update product");
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeCon(conn);
        }
        return false;
    }


}
