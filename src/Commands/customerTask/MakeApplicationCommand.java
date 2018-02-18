package Commands.customerTask;

import Commands.Attributes;
import Commands.Command;
import Commands.Messages;
import Commands.Paths;
import MySQL.Fields;
import MySQL.JavaBeans.UserAccount;
import MySQL.tables.ApplicationsTable;
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
        String forward = Paths.JSP_HOME;

        try {
            if (!ApplicationsTable.insertApplication(user.getUserID(), Integer.parseInt(req.getParameter(Fields.SLEEPS)),
                    req.getParameter(Fields.CLASS), req.getParameter(Fields.CHECK_IN), req.getParameter(Fields.CHECK_OUT))) {
                message = Messages.CANT_INSERT_APPLICATION;
                req.getSession().setAttribute(Attributes.HOME_VIEW_MESSAGE, message);
            } else {
                message = Messages.INSERT_APPLICATION_SUCCESSES;
            }
        } catch (ParseException e) {
            message = Messages.DATE_FIELDS_ERROR;
            req.getSession().setAttribute(Attributes.HOME_VIEW_MESSAGE, message);
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
            message = Messages.NOT_LOGGED_IN;
            req.getSession().setAttribute(Attributes.HOME_VIEW_MESSAGE, message);
        }

        req.getSession().setAttribute(Attributes.HOME_VIEW_MESSAGE, message);
        return forward;
    }

}
