package Commands.customerTask;

import Commands.Command;
import MySQL.ApplicationsTable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CancelApplication extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException {
        String forward = "controller?command=dashboard";
        String appId = req.getParameter("appId");
        System.out.println(appId);
        ApplicationsTable.cancelApplication(appId);
        return forward;
    }
}
