package Commands.managerTask;

import Commands.Attributes;
import Commands.Command;
import Commands.Messages;
import Commands.Paths;
import MySQL.Fields;
import MySQL.tables.ApplicationResponseTable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SubmitSuggestionCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String forward = Paths.COMMAND_MANAGER;


        if (ApplicationResponseTable.insertResponse(req.getParameter(Attributes.APP_ID),
                Integer.parseInt(req.getParameter(Attributes.USER_ID)), Integer.parseInt(req.getParameter(Fields.ROOM_NO)))) {
            req.getSession().setAttribute(Attributes.MESSAGE, Messages.SUGGESTION_HAS_BEEN_SENT);
        } else {
            req.getSession().setAttribute(Attributes.MESSAGE, Messages.DUPLICATE_SUGGESTION);
        }
        return forward;
    }
}
