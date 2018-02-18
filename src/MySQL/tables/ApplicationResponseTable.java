package MySQL.tables;

import MySQL.ConnectionUtils;
import MySQL.Fields;
import MySQL.JavaBeans.Application;

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
                ar.add(new Application(rs.getString(Fields.STRING), rs.getInt(Fields.SLEEPS),
                        rs.getString(Fields.CLASS),
                        rs.getString(Fields.CHECK_IN),rs.getString(Fields.CHECK_OUT),
                        rs.getInt(Fields.ROOM_NO),rs.getDouble(Fields.PRICE), rs.getString(Fields.IMAGE)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ar;
    }

    public static boolean insertResponse(String applicId, int userId, int roomNo) {
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement prstm = conn.prepareStatement(INSERT_RESPONSE);
            int k = 1;
            prstm.setString(k++, applicId);
            prstm.setInt(k++, userId);
            prstm.setInt(k, roomNo);
            prstm.executeUpdate();
            conn.commit();
            return true;
        } catch (SQLException e) {
            ConnectionUtils.rollback(conn);
            e.printStackTrace();
            System.out.println("cant insert Response");
        } finally {
            ConnectionUtils.closeCon(conn);
        }
        return false;
    }

}