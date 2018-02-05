package Commands.customerTask;

import Commands.Command;
import MySQL.ApplicationResponseTable;
import MySQL.ApplicationsTable;
import MySQL.OrdersTable;
import Utils.AppUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DashboardCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp)  {
        req.setAttribute("reqFrom", req.getParameter("command"));
        String forward = "/Views/dashboard.jsp";
        try {
            System.out.println("PersonalCabinet#doGet");
            List orderList = OrdersTable.getOrderByUserId(AppUtils.getLoginedUser(req.getSession()).getUserID());
            List applList = ApplicationsTable.extractForUser(AppUtils.getLoginedUser(req.getSession()).getUserID());
            List offerList = ApplicationResponseTable.getResponseForUser(AppUtils.getLoginedUser(req.getSession()).getUserID());
            System.out.println(offerList);
            req.setAttribute("orderList", orderList);
            req.setAttribute("applList", applList);
            req.setAttribute("offerList", offerList);
            if (orderList.size() == 0) {
                req.setAttribute("message", "You have no pending orders yet" + "\n Proceed to products to make an order");
            }
            if (applList.size() == 0) {
                req.setAttribute("applMessage", "You have no applications yet");
            }
            if (offerList.size() == 0) {
                req.setAttribute("offerMessage", "You have no offers for your applications");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            return forward;
        }
        return forward;
    }
}
