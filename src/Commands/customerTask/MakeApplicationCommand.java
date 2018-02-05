package Commands.customerTask;

import Commands.Command;
import JavaBeans.UserAccount;
import MySQL.ApplicationsTable;
import Utils.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

public class MakeApplicationCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        UserAccount user = AppUtils.getLoginedUser(req.getSession());
        String message;
        String forward ="/homeView.jsp";
        try {
            try {
                if (!ApplicationsTable.insertApplication(user.getUserID(), Integer.parseInt(req.getParameter("sleeps")),
                        req.getParameter("class"), req.getParameter("checkIn"), req.getParameter("checkOut"))) {
                    message = "Please specify correct date range";
                    req.setAttribute("message", message);
                } else {
                    message = "You have successfully made an application. Please visit Your Dashboard to check for an offer";
                }
            } catch (ParseException e) {
                message = "Please, fill in all required date fields";
                req.setAttribute("message", message);
                e.printStackTrace();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            message = "Sorry, You have to login prior to making applications";
            req.setAttribute("message", message);
        }

        req.setAttribute("message", message);
        return forward;
    }

}
