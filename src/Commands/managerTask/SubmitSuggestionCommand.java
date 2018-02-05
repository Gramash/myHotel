package Commands.managerTask;

import Commands.Command;
import MySQL.ApplicationResponseTable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SubmitSuggestionCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String forward = "/controller?command=managerTask";

        if (ApplicationResponseTable.insertResponse(req.getParameter("appId"),
                Integer.parseInt(req.getParameter("userId")), Integer.parseInt(req.getParameter("roomNo")))) {
            System.out.println("response sent");
        } else {
            forward = "/Views/accessDenied.jsp";
        }
        return forward;
    }
}
