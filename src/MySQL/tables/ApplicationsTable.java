package MySQL.tables;

import MySQL.ConnectionUtils;
import MySQL.Fields;
import MySQL.JavaBeans.Application;
import Utils.DateUtils;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ApplicationsTable {


    private static final String INSERT_APPLICATION = "INSERT INTO applications VALUES (?,?,?,?,?,?, DEFAULT )";
    private static final String EXTRACT_ALL = "SELECT applications.user_id, application_id, sleeps, " +
            "applications.class, checkIn, checkOut, users.email, users.name " +
            "FROM applications " +
            "INNER JOIN users ON users.user_id = applications.user_id WHERE completed = 0";
    private static final String EXTRACT_FOR_USER = "SELECT application_id, sleeps, applications.class, checkIn, checkOut, users.email, users.name " +
            "FROM applications " +
            "INNER JOIN users ON users.user_id = applications.user_id " +
            "WHERE applications.user_id = ? AND completed = 0";
    private static final String CLOSE_APPLICATION = "UPDATE applications SET completed = 1 WHERE application_id = ?";
    private static final String REMOVE_OUTDATED = "DELETE FROM applications WHERE checkIn <= curdate() AND completed = 0";
    private static final String REMOVE_APP = "DELETE FROM applications WHERE application_id=?";

    private static int createApplId() {
        Random random = new Random();
        return random.nextInt(9999);
    }

    public static boolean insertApplication(int userId, int sleeps, String clazz, String checkIn, String checkOut) throws ParseException {
        if ( DateUtils.isBeforeToday(
                DateUtils.dateToString(
                        DateUtils.addDays(
                                DateUtils.stringToDate(checkIn), -3)))) {
            return false;
        }
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement prstm = conn.prepareStatement(INSERT_APPLICATION);
            int k = 1;
            prstm.setString(k++, "#" + createApplId());
            prstm.setInt(k++, userId);
            prstm.setInt(k++, sleeps);
            prstm.setString(k++, clazz);
            prstm.setString(k++, checkIn);
            prstm.setString(k, checkOut);
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

    public static List<Application> extractAll() {
        removeOutdated();
        List<Application> list = null;
        ResultSet rs;
        try (Connection conn = ConnectionUtils.getConnection()) {
            PreparedStatement prstm = conn.prepareStatement(EXTRACT_ALL);
            rs = prstm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                Application app = new Application(rs.getString(Fields.STRING),
                        rs.getInt(Fields.SLEEPS), rs.getString(Fields.CLASS),
                        rs.getString(Fields.CHECK_IN), rs.getString(Fields.CHECK_OUT),
                        rs.getString(Fields.EMAIL), rs.getString(Fields.NAME), rs.getInt(Fields.USER_ID));
                list.add(app);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Application> extractForUser(int userId) {
        removeOutdated();
        List<Application> list = null;
        ResultSet rs;
        try (Connection conn = ConnectionUtils.getConnection()) {
            PreparedStatement prstm = conn.prepareStatement(EXTRACT_FOR_USER);
            prstm.setInt(1, userId);
            rs = prstm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                Application app = new Application(rs.getString(Fields.STRING),
                        rs.getInt(Fields.SLEEPS), rs.getString(Fields.CLASS), rs.getString(Fields.CHECK_IN),
                        rs.getString(Fields.CHECK_OUT),
                        rs.getString(Fields.EMAIL), rs.getString(Fields.NAME), userId);
                list.add(app);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("cant ApprlTable#extractForUser");
        }
        return list;
    }

    public static boolean closeApplication(String appId) {
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement prstm = conn.prepareStatement(CLOSE_APPLICATION);
            prstm.setString(1, appId);
            prstm.executeUpdate();
            conn.commit();
            return true;
        } catch (SQLException e) {
            ConnectionUtils.rollback(conn);
            e.printStackTrace();
            System.out.println("cannot closeApplication");
        } finally {
            ConnectionUtils.closeCon(conn);
        }
        return false;
    }

    private static boolean removeOutdated() {
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement prstm = conn.prepareStatement(REMOVE_OUTDATED);
            prstm.executeUpdate();
            conn.commit();
            return true;
        } catch (SQLException e) {
            ConnectionUtils.rollback(conn);
            System.out.println("failed to ApllicationsTable#removeOutdated");
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeCon(conn);
        }
        return false;
    }

    public static boolean cancelApplication(String appId) {
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();
            PreparedStatement prstm = conn.prepareStatement(REMOVE_APP);
            prstm.setString(1, appId);
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
