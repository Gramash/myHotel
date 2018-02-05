import Commands.Command;
import Commands.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet ({"/controller", "/"})
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req,
                         HttpServletResponse resp) throws IOException, ServletException {

        System.out.println("controller started working");
        // extract command name from the request
        String commandName = req.getParameter("command");
        System.out.println(commandName);
        // obtain command object by its name
        Command command = CommandContainer.get(commandName);
        // execute command and get forward address
        String forward = "Views/accessDeniedView.jsp";
        try {
            forward = command.execute(req, resp);
        } catch (Exception ex) {
            System.out.println("you are in catch ex");
            req.setAttribute("errorMessage", ex.getMessage());
            ex.printStackTrace();

        }
        // go to forward
        req.getRequestDispatcher(forward).forward(req, resp);
    }

}
