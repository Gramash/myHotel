package Commands.generalCommands;

import Commands.Attributes;
import Commands.Command;
import Commands.Messages;
import Commands.Paths;
import MySQL.JavaBeans.UserAccount;
import MySQL.tables.UserTable;
import Utils.EmailUtils;
import Utils.PasswordEncryption;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static MySQL.tables.UserTable.insertUser;

public class RegisterCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String userLogin = request.getParameter(Attributes.USER_LOGIN);
        String password = null;
        String userName = request.getParameter(Attributes.USER_NAME);
        String email = request.getParameter(Attributes.USER_EMAIL);

        try {
            password = PasswordEncryption.getSaltedHash(request.getParameter(Attributes.USER_PASSWORD));
            System.out.println(password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String forward = Paths.JSP_REGISTER_VIEW;
//
        if (!insertUser(userLogin, userName, password, email)) {
            String message;
            UserAccount user = UserTable.findByEmail(email);
            if (user != null && user.getUserLogin() != null) {
                message = Messages.LOGIN_IS_TAKEN;
                request.getSession().setAttribute(Attributes.ERROR_REGISTER_MESSAGE, message);
                return forward;
            }
        }
        forward = Paths.JSP_LOGIN;
        String message = Messages.SUCCESSES_LOG_IN;
        request.getSession().setAttribute(Attributes.WELCOME_MESSAGE, message);
        String emailMessage = "Hello " + userName + "! \nCongrats on becoming a part of our Family!\n" +
                "Your login is: " + userLogin +
                "\n Looking forward to meet You at our Hotel! \n" +
                "Best Regards,\n" +
                "GG Hotel team.";
        EmailUtils.send(email, "Welcome!", emailMessage);
        return forward;
    }
}
