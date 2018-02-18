package Commands.managerTask;

import Commands.Attributes;
import Commands.Command;
import Commands.Paths;
import MySQL.Fields;
import MySQL.JavaBeans.Product;
import MySQL.tables.ProductTable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class FilterApplications extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String sleeps = req.getParameter(Fields.SLEEPS);
        String checkIn = req.getParameter(Fields.CHECK_IN);
        String checkOut = req.getParameter(Fields.CHECK_OUT);
        String clazz = req.getParameter(Fields.CLASS);
        String forward = Paths.JSP_ACCESS_DENIED;
        List<Product> listSuggestions;
        try {
            listSuggestions = ProductTable.selectSuitable(Integer.parseInt(sleeps), checkIn, checkOut, clazz);
        } catch (ParseException e) {
            e.printStackTrace();
            return forward;
        }
        System.out.println(listSuggestions.size());
        req.setAttribute(Attributes.APP_ID, req.getParameter(Attributes.APP_ID));
        req.setAttribute(Attributes.USER_ID, req.getParameter(Attributes.USER_ID));
        req.setAttribute(Attributes.SUGGESTION_LIST, listSuggestions);
        forward = Paths.COMMAND_MANAGER;
        return forward;
    }
}
