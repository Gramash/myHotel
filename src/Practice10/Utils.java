package Practice10;

import JavaBeans.UserAccount;

import javax.servlet.http.HttpSession;
import java.util.List;

public class Utils {
    public static void storeUserList(HttpSession session, List list) {

        session.setAttribute("list", list);
    }

    public static void storeUser(HttpSession session, User user) {

        session.setAttribute("user", user);
    }

    public static List getUserList(HttpSession session) {

        return (List) session.getAttribute("list");
    }

}
