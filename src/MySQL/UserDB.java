package MySQL;

import JavaBeans.UserAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDB {

    private static final String FIND_BY_LOGIN_AND_PASS = "select * from users WHERE login = ? and password =?";
    private static final String FIND_BY_LOGIN = "select * from users WHERE email = ? ";
    private static final String INSERT_USER = "insert into users values (default,?,?,?,?, default)";

    public static UserAccount findByLogin(String login, String password) {
        try (Connection conn = ConnectionUtils.getConnection()) {
            PreparedStatement prstm = conn.prepareStatement(FIND_BY_LOGIN_AND_PASS);
            int k = 1;
            prstm.setString(k++, login);
            prstm.setString(k, password);
            ResultSet rs = prstm.executeQuery();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static UserAccount findByEmail(String login) {
        try (Connection conn = ConnectionUtils.getConnection()) {
            PreparedStatement prstm = conn.prepareStatement(FIND_BY_LOGIN);
            int k = 1;
            prstm.setString(k++, login);
            ResultSet rs = prstm.executeQuery();
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

}
