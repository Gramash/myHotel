package MySQL;

import JavaBeans.Product;
import JavaBeans.UserAccount;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDB {


    private static final String FIND_ALL_PRODUCTS = "select * from products order by roomNo";


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

}
