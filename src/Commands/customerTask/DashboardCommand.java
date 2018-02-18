package Commands.customerTask;

import Commands.Attributes;
import Commands.Command;
import Commands.Messages;
import Commands.Paths;
import MySQL.tables.ApplicationResponseTable;
import MySQL.tables.ApplicationsTable;
import MySQL.tables.OrdersTable;
import Utils.AppUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DashboardCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp)  {
        req.setAttribute(Attributes.REQUEST_FROM, req.getParameter(Attributes.COMMAND));
        String forward = Paths.JSP_DASHBOARD;
        try {
            List orderList = OrdersTable.getOrderByUserId(AppUtils.getLoginedUser(req.getSession()).getUserID());
            List applList = ApplicationsTable.extractForUser(AppUtils.getLoginedUser(req.getSession()).getUserID());
            List offerList = ApplicationResponseTable.getResponseForUser(AppUtils.getLoginedUser(req.getSession()).getUserID());
            System.out.println(offerList);
            req.setAttribute(Attributes.ORDER_LIST, orderList);
            req.setAttribute(Attributes.APPLICATION_LIST, applList);
            req.setAttribute(Attributes.OFFER_LIST, offerList);
            if (orderList.size() == 0) {
                req.setAttribute(Attributes.MESSAGE, Messages.NO_PENDING_ORDERS);
            }
            if (applList.size() == 0) {
                req.setAttribute(Attributes.APPLICATION_MESSAGE, Messages.NO_PENDING_APPLICATIONS);
            }
            if (offerList.size() == 0) {
                req.setAttribute(Attributes.OFFER_MESSAGE, Messages.NO_OFFERS_FOR_APPLICATION);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            return forward;
        }
        return forward;
    }
}
