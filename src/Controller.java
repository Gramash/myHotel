import Commands.Attributes;
import Commands.Command;
import Commands.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet({"/controller", "/"})
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
        req.getSession().removeAttribute(Attributes.MESSAGE);
        req.getSession().removeAttribute(Attributes.ERROR_MESSAGE);
        req.getSession().removeAttribute(Attributes.WELCOME_MESSAGE);
        req.getSession().removeAttribute(Attributes.ERROR_REGISTER_MESSAGE);
        req.getSession().removeAttribute(Attributes.HOME_VIEW_MESSAGE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);

    }

    private void process(HttpServletRequest req,
                         HttpServletResponse resp) throws IOException, ServletException {

        String commandName = req.getParameter(Attributes.COMMAND);

        Command command = CommandContainer.get(commandName);

        String forward = "Views/accessDeniedView.jsp";
        try {
            forward = command.execute(req, resp);
        } catch (Exception ex) {
            req.setAttribute(Attributes.ERROR_MESSAGE, ex.getMessage());
            ex.printStackTrace();

        }

//        String method = req.getMethod();
//        if (method.equalsIgnoreCase("get")) {
            req.getRequestDispatcher(forward).forward(req, resp);
//        } else {
//            resp.sendRedirect(forward);
//        }


    }



}
