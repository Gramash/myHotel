package MySQL.tables;

import MySQL.ConnectionUtils;
import MySQL.Fields;
import MySQL.JavaBeans.UserAccount;
import Utils.PasswordEncryption;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTable {
    private static final String FIND_BY_LOGIN_AND_PASS = "SELECT * FROM users WHERE login = ? ";
    private static final String FIND_BY_EMAIL = "SELECT * FROM users WHERE email = ? ";
    private static final String INSERT_USER = "INSERT INTO users VALUES (default,?,?,?,?, default)";
    private static final String FIND_BY_ID = "SELECT * FROM users WHERE user_id= ?";
    private static final String GET_PASS_BY_LOGIN = "SELECT password FROM users WHERE login =?";


    public static UserAccount findByLogin(String login, String password) {
        if (!PasswordEncryption.check(password, getPassByLogin(login))) {
            return null;
        }
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement prstm = conn.prepareStatement(FIND_BY_LOGIN_AND_PASS);
            int k = 1;
            prstm.setString(k, login);
            ResultSet rs = prstm.executeQuery();
            UserAccount user = getUserAccount(rs);
            conn.commit();
            if (user != null) return user;
        } catch (SQLException e) {
            ConnectionUtils.rollback(conn);
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeCon(conn);
        }
        return null;
    }

    private static String getPassByLogin(String login) {
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement prstm = conn.prepareStatement(GET_PASS_BY_LOGIN);
            int k = 1;
            prstm.setString(k, login);
            ResultSet rs = prstm.executeQuery();
            rs.next();
            conn.commit();
            return rs.getString(Fields.PASSWORD);
        } catch (SQLException e) {
            ConnectionUtils.rollback(conn);
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeCon(conn);
        }
        return "";
    }

    private static UserAccount getUserAccount(ResultSet rs) throws SQLException {
        if (rs.next()) {
            UserAccount user = new UserAccount();
            user.setUserID(Integer.parseInt(rs.getString(Fields.USER_ID)));
            user.setUserLogin(rs.getString(Fields.LOGIN));
            user.setPassword(rs.getString(Fields.PASSWORD));
            user.setUserName(rs.getString(Fields.NAME));
            user.setAccessLevel(rs.getString(Fields.ROLE));
            user.setEmail(rs.getString(Fields.EMAIL));
            return user;
        }
        return null;
    }

    public static UserAccount findByEmail(String login) {
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement prstm = conn.prepareStatement(FIND_BY_EMAIL);
            int k = 1;
            prstm.setString(k, login);
            ResultSet rs = prstm.executeQuery();
            UserAccount user = getUserAccount(rs);
            conn.commit();
            return user;
        } catch (Exception e) {
            ConnectionUtils.rollback(conn);
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeCon(conn);
        }
        return null;
    }

    public static boolean insertUser(String login, String name, String password, String email) {
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();
            PreparedStatement prstm = conn.prepareStatement(INSERT_USER);
            int k = 1;
            prstm.setString(k++, login);
            prstm.setString(k++, name);
            prstm.setString(k++, password);
            prstm.setString(k, email);
            prstm.execute();
            conn.commit();
            return true;
        } catch (Exception e) {
            ConnectionUtils.rollback(conn);
            e.printStackTrace();
            return false;
        } finally {
            ConnectionUtils.closeCon(conn);
        }
    }

    public static UserAccount findById(int userId) {
        UserAccount user = null;
        Connection conn = null;
        try  {
            conn = ConnectionUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement prstm = conn.prepareStatement(FIND_BY_ID);
            int k = 1;
            prstm.setInt(k, userId);
            ResultSet rs = prstm.executeQuery();
            rs.next();
            user = new UserAccount();
            user.setUserID(userId);
            user.setEmail(rs.getString(Fields.EMAIL));
            user.setUserName(rs.getString(Fields.NAME));
            user.setUserLogin(Fields.LOGIN);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("cant find user By Id");
        } finally {
            ConnectionUtils.closeCon(conn);
        }
        return user;
    }


}
