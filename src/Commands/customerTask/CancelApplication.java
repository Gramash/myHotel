package Commands.customerTask;

import Commands.Attributes;
import Commands.Command;
import Commands.Paths;
import MySQL.tables.ApplicationsTable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CancelApplication extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException {
        String forward = Paths.COMMAND_DASHBOARD;
        String appId = req.getParameter(Attributes.APP_ID);
        System.out.println(appId);
        ApplicationsTable.cancelApplication(appId);
        return forward;
    }
}
