package Commands.generalCommands;

import Commands.Attributes;
import Commands.Command;
import Commands.Messages;
import Commands.Paths;
import MySQL.JavaBeans.UserAccount;
import MySQL.tables.UserTable;
import Utils.AppUtils;
import config.SecurityConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCommand extends Command {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userLogin = request.getParameter(Attributes.USER_LOGIN);
        String password = request.getParameter(Attributes.USER_PASSWORD);

        UserAccount userAccount = UserTable.findByLogin(userLogin, password);
        String forward = Paths.JSP_LOGIN;

        if (userAccount == null) {
            String errorMessage = Messages.INVALID_LOGIN_PASSWORD;

            request.getSession().setAttribute(Attributes.ERROR_MESSAGE, errorMessage);
            return forward;
        }

        forward = Paths.JSP_HOME;
        String message = "Welcome, " + userAccount.getUserName() + "!";
        request.getSession().setAttribute(Attributes.HOME_VIEW_MESSAGE, message);


        AppUtils.storeLoginedUser(request.getSession(), userAccount);
        request.getSession().setAttribute(Attributes.ROLE, SecurityConfig.ROLE_MANAGER);


        return forward;


    }
}
