package Commands.generalCommands;

import Commands.Attributes;
import Commands.Command;
import Commands.Messages;
import Commands.Paths;
import MySQL.Fields;
import MySQL.JavaBeans.Product;
import MySQL.tables.ProductTable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;

public class ProductViewCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Product> productList;
        String orderBy = request.getParameter(Attributes.ORDER_BY);
        String order = request.getParameter(Attributes.ORDER);
        if (orderBy == null) {
            orderBy = Fields.ROOM_NO;
        }
        if (order == null) {
            order = Attributes.ASCENDING;
        }
        productList = ProductTable.extractAll(false, orderBy, order);

        String sleeps = request.getParameter(Fields.SLEEPS);
        String clazz = request.getParameter(Fields.CLASS);
        String checkIn = request.getParameter(Fields.CHECK_IN);
        String checkOut = request.getParameter(Fields.CHECK_OUT);

        if(sleeps!=null && clazz!=null &&
                checkIn!=null && checkOut!=null){
            try {
                productList = ProductTable.selectSuitable(Integer.parseInt(sleeps), checkIn, checkOut, clazz);
            } catch (ParseException e) {
                e.printStackTrace();
                request.getSession().setAttribute(Attributes.MESSAGE, Messages.DATE_FIELDS_ERROR);
            }

        }
        request.setAttribute(Attributes.REQUEST_FROM, request.getParameter(Attributes.COMMAND));
        request.setAttribute(Attributes.PRODUCT_LIST, productList);
        return Paths.JSP_PRODUCT_VIEW;

    }
}
