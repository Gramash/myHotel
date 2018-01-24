package MySQL;

import JavaBeans.Application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class ApplicationsTable {


    private static final String INSERT_APPLICATION = "insert into applications values (?,?,?,?,?)";
    private static final String EXTRACT_ALL = "select applications.user_id, application_id, sleeps, checkIn, checkOut, users.email, users.name " +
            "from applications " +
            "inner join users ON users.user_id = applications.user_id";
    private static final String EXTRACT_FOR_USER = "select application_id, sleeps, checkIn, checkOut, users.email, users.name\n" +
            "from applications\n" +
            "inner join users ON users.user_id = applications.user_id \n" +
            "where applications.user_id = ?";

    private static int createApplId() {
        Random random = new Random();
        return random.nextInt(9999);
    }

    public static boolean insertApplication(int userId, int sleeps, String checkIn, String checkOut) {
        try (Connection conn = ConnectionUtils.getConnection()) {
            PreparedStatement prstm = conn.prepareStatement(INSERT_APPLICATION);
            int k = 1;
            prstm.setString(k++, "#" + createApplId());
            prstm.setInt(k++, userId);
            prstm.setInt(k++, sleeps);
            prstm.setString(k++, checkIn);
            prstm.setString(k, checkOut);
            prstm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Application> extractAll() {
        List<Application> list = null;
        ResultSet rs;
        try (Connection conn = ConnectionUtils.getConnection()) {
            PreparedStatement prstm = conn.prepareStatement(EXTRACT_ALL);
            rs = prstm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                Application app = new Application(rs.getString("application_id"),
                        rs.getInt("sleeps"), rs.getString("checkIn"), rs.getString("checkOut"),
                        rs.getString("email"), rs.getString("name"), rs.getInt("user_id"));
                list.add(app);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Application> extractForUser(int userId) {
        List<Application> list = null;
        ResultSet rs;
        try (Connection conn = ConnectionUtils.getConnection()) {
            PreparedStatement prstm = conn.prepareStatement(EXTRACT_FOR_USER);
            prstm.setInt(1, userId);
            rs = prstm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                Application app = new Application(rs.getString("application_id"),
                        rs.getInt("sleeps"), rs.getString("checkIn"), rs.getString("checkOut"),
                        rs.getString("email"), rs.getString("name"), userId);
                list.add(app);
            }
        } catch (SQLException e) {

        }
        return list;
    }
}
