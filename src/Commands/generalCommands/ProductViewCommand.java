package Commands.generalCommands;

import Commands.Command;
import JavaBeans.Product;
import MySQL.ProductTable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;

public class ProductViewCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Product> productList;
        String orderBy = request.getParameter("orderBy");
        String order = request.getParameter("order");
        if (orderBy == null) {
            orderBy = "roomNo";
        }
        if (order == null) {
            order = "asc";
        }
        productList = ProductTable.extractAll(false, orderBy, order);

        String sleeps = request.getParameter("sleeps");
        String clazz = request.getParameter("class");
        String checkIn = request.getParameter("checkIn");
        String checkOut = request.getParameter("checkOut");

        if(sleeps!=null && clazz!=null &&
                checkIn!=null && checkOut!=null){
            try {
                productList = ProductTable.selectSuitable(Integer.parseInt(sleeps), checkIn, checkOut, clazz);
            } catch (ParseException e) {
                e.printStackTrace();
                request.setAttribute("message", "Please fill in dates");
            }

        }
        request.setAttribute("reqFrom", request.getParameter("command"));
        request.setAttribute("productList", productList);
        return "Views/ProductView.jsp";

    }
}
