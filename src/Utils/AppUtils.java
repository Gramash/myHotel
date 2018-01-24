package Utils;

import JavaBeans.UserAccount;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;


public class AppUtils {

    // Save user info in session
    public static void storeLoginedUser(HttpSession session, UserAccount loginedUser) {
        session.setAttribute("loginedUser", loginedUser);
    }

    // get info about user stored in session
    public static UserAccount getLoginedUser(HttpSession session) {
        return (UserAccount) session.getAttribute("loginedUser");
    }
}

