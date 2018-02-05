package Commands.generalCommands;

import Commands.Command;
import JavaBeans.UserAccount;
import MySQL.UserTable;
import Utils.AppUtils;
import config.SecurityConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCommand extends Command {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userLogin = request.getParameter("userLogin");
        String password = request.getParameter("password");

        UserAccount userAccount = UserTable.findByLogin(userLogin, password);
        String forward = "/Views/loginView.jsp";

        if (userAccount == null) {
            String errorMessage = "Invalid Login or Password";

            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }
        forward= "/homeView.jsp";
        String message = "Welcome, " + userAccount.getUserName() + "!";
        request.setAttribute("message", message);


        AppUtils.storeLoginedUser(request.getSession(), userAccount);
        request.getSession().setAttribute("role", SecurityConfig.ROLE_MANAGER);


        return forward;


    }
}
