package Commands.managerTask;

import Commands.Attributes;
import Commands.Command;
import Commands.Messages;
import Commands.Paths;
import MySQL.Fields;
import MySQL.JavaBeans.Order;
import MySQL.tables.ApplicationsTable;
import MySQL.tables.OrdersTable;
import MySQL.tables.ProductTable;
import Utils.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ManagerTaskCommand extends Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String forward = Paths.JSP_MANAGER_TASK;
        List<Order> orderList;
        orderList = OrdersTable.getOrderManager();
        List applList = ApplicationsTable.extractAll();
        List productsList = ProductTable.extractAll(true, Fields.ROOM_NO, Attributes.ASCENDING);
        List orderPriceList = OrdersTable.getOrdersPrice();
        req.setAttribute("orderPriceList",orderPriceList);
        req.setAttribute(Attributes.PRODUCT_LIST, productsList);
        req.setAttribute(Attributes.APPLICATION_LIST, applList);
        req.setAttribute(Attributes.ORDER_LIST, orderList);


        if (req.getParameter(Attributes.CHECK_IN_CHECK)!= null && req.getParameter(Attributes.CHECK_OUT_CHECK)!=null) {
            for (Order order : orderList) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String date1 = df.format(order.getCheckIn());
                String date2 = df.format(order.getCheckOut());
                req.setAttribute(Attributes.CHECK_IN_CHECK, req.getParameter(Attributes.CHECK_IN_CHECK));
                req.setAttribute(Attributes.CHECK_OUT_CHECK, req.getParameter(Attributes.CHECK_OUT_CHECK));
                try {
                    if (DateUtils.datesOverlap(date1, date2,
                            req.getParameter(Attributes.CHECK_IN_CHECK), req.getParameter(Attributes.CHECK_OUT_CHECK))) {
                        req.setAttribute(Attributes.DATES_CHECK_MESS, Messages.DATES_ARE_TAKEN);
                    } else {
                        req.setAttribute(Attributes.DATES_CHECK_MESS, Messages.DATE_ARE_FREE);
                    }
                } catch (ParseException e){
                    req.setAttribute(Attributes.DATES_CHECK_MESS, Messages.DATE_FIELDS_ERROR);
                }
            }
        }
        return forward;

    }
}
