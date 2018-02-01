package MySQL;

import JavaBeans.Application;
import JavaBeans.ApplicationResponse;
import JavaBeans.UserAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationResponseTable {

    private static final String EXTRACT_RESPONSE_FOR_USER = "select applications_products.application_id, products.roomNo, " +
            "products.sleeps, applications.checkIn, applications.checkOut,price, image, products.class " +
            "from applications_products  " +
            "inner join products ON products.roomNo = applications_products.roomNo " +
            "join applications ON applications.application_id = applications_products.application_id " +
            "where applications_products.user_id = ? and completed=0";
    private static final String INSERT_RESPONSE = "INSERT INTO applications_products values (?,?,?)";

    public static List<Application> getResponseForUser(int userId) {
        ResultSet rs;
        List<Application> ar = null;
        try (Connection conn = ConnectionUtils.getConnection()) {
            PreparedStatement prstm = conn.prepareStatement(EXTRACT_RESPONSE_FOR_USER);
            int k = 1;
            prstm.setInt(k, userId);
            rs = prstm.executeQuery();
            ar = new ArrayList<>();
            while (rs.next()) {
                ar.add(new Application(rs.getString("application_id"), rs.getInt("sleeps"), rs.getString("class"),
                        rs.getString("checkIn"),rs.getString("checkOut"),
                        rs.getInt("roomNo"),rs.getDouble("price"), rs.getString("image")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ar;
    }

    public static boolean insertResponse(String applicId, int userId, int roomNo) {
        try (Connection conn = ConnectionUtils.getConnection()) {
            PreparedStatement prstm = conn.prepareStatement(INSERT_RESPONSE);
            int k = 1;
            prstm.setString(k++, applicId);
            prstm.setInt(k++, userId);
            prstm.setInt(k, roomNo);
            prstm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("cant insert Response");
        }
        return false;
    }

}