package MySQL;

import JavaBeans.UserAccount;
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
        try (Connection conn = ConnectionUtils.getConnection()) {
            PreparedStatement prstm = conn.prepareStatement(FIND_BY_LOGIN_AND_PASS);
            int k = 1;
            prstm.setString(k, login);
            ResultSet rs = prstm.executeQuery();
            UserAccount user = getUserAccount(rs);
            if (user != null) return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getPassByLogin(String login) {
        try (Connection conn = ConnectionUtils.getConnection()) {
            PreparedStatement prstm = conn.prepareStatement(GET_PASS_BY_LOGIN);
            int k = 1;
            prstm.setString(k, login);
            ResultSet rs = prstm.executeQuery();
            rs.next();
            return rs.getString("password");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static UserAccount getUserAccount(ResultSet rs) throws SQLException {
        if (rs.next()) {
            UserAccount user = new UserAccount();
            user.setUserID(Integer.parseInt(rs.getString("user_id")));
            user.setUserLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setUserName(rs.getString("name"));
            user.setAccessLevel(rs.getString("role"));
            user.setEmail(rs.getString("email"));
            return user;
        }
        return null;
    }

    public static UserAccount findByEmail(String login) {
        try (Connection conn = ConnectionUtils.getConnection()) {
            PreparedStatement prstm = conn.prepareStatement(FIND_BY_EMAIL);
            int k = 1;
            prstm.setString(k, login);
            ResultSet rs = prstm.executeQuery();
            UserAccount user = getUserAccount(rs);
            if (user != null) return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean insertUser(String login, String name, String password, String email) {
        try (Connection conn = ConnectionUtils.getConnection()) {
            PreparedStatement prstm = conn.prepareStatement(INSERT_USER);
            int k = 1;
            prstm.setString(k++, login);
            prstm.setString(k++, name);
            prstm.setString(k++, password);
            prstm.setString(k, email);
            prstm.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static UserAccount findById(int userId) {
        UserAccount user = null;
        try (Connection conn = ConnectionUtils.getConnection()) {
            PreparedStatement prstm = conn.prepareStatement(FIND_BY_ID);
            int k = 1;
            prstm.setInt(k, userId);
            ResultSet rs = prstm.executeQuery();
            rs.next();
            user = new UserAccount();
            user.setUserID(userId);
            user.setEmail(rs.getString("email"));
            user.setUserName(rs.getString("name"));
            user.setUserLogin("login");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("cant find user By Id");
        }
        return user;
    }


}
