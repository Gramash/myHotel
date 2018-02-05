package Commands.managerTask;

import Commands.Command;
import JavaBeans.Product;
import MySQL.ProductTable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class FilterApplications extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String sleeps = req.getParameter("sleeps");
        String checkIn = req.getParameter("checkIn");
        String checkOut = req.getParameter("checkOut");
        String clazz = req.getParameter("class");
        String forward = "/Views/accessDenied";
        List<Product> listSuggestions;
        try {
            listSuggestions = ProductTable.selectSuitable(Integer.parseInt(sleeps), checkIn, checkOut, clazz);
        } catch (ParseException e) {
            e.printStackTrace();
            return forward;
        }
        req.setAttribute("appId", req.getParameter("appId"));
        req.setAttribute("userId", req.getParameter("userId"));
        req.setAttribute("suggestionsList", listSuggestions);
        forward = "/controller?command=managerTask";
        return forward;
    }
}
