package MySQL;

import JavaBeans.Product;
import JavaBeans.UserAccount;
import Utils.DateUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductTable {

    private static final String FIND_ALL_PRODUCTS = "select * from products order by roomNo";
    private static final String FIND_SUITABLE = "Select * from products " +
            "LEFT JOIN orders ON products.roomNo = orders.product_id " +
            "WHERE sleeps =?";
    private static final String FIND_BY_ID = "Select * from products " +
            "LEFT JOIN orders ON products.roomNo = orders.product_id " +
            "WHERE roomNo=?";


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

}
