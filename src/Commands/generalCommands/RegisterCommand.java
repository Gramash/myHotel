package Commands.generalCommands;

import Commands.Command;
import Utils.EmailUtils;
import Utils.PasswordEncryption;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static MySQL.UserTable.insertUser;

public class RegisterCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String userLogin = request.getParameter("userLogin");
        String password = null;
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        try {
            password = PasswordEncryption.getSaltedHash(request.getParameter("password"));
            System.out.println(password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String forward = "/Views/RegisterView";
//
        if (!insertUser(userLogin, userName, password, email)) {
            String message = "failed";
            request.setAttribute("errorMessage", message);
            return forward;
        }
        forward = "/Views/loginView.jsp";
        String message = "Congrats! You have successfully signed up. Welcome to our family!\n" +
                "Please enter your login and pass to proceed to Your personal cabinet";
        request.setAttribute("welcomeMessage", message);
        String emailMessage = "Hello " + userName + "! \nCongrats on becoming a part of our Family!\n" +
                "Your login is: " + userLogin +
                "\nYour pass is: " + password +
                "\n Looking forward to meet You at out Hotel! \n" +
                "Best Regards,\n" +
                "GG Hotel team.";
        EmailUtils.send(email, "Welcome!", emailMessage);
        return forward;
    }
}
