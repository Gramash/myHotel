package Commands.managerTask;

import Commands.Command;
import JavaBeans.Order;
import MySQL.ApplicationsTable;
import MySQL.OrdersTable;
import MySQL.ProductTable;
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
        String forward = "/Views/managerTaskView.jsp";
        List<Order> orderList;
        orderList = OrdersTable.getOrderManager();
        List applList = ApplicationsTable.extractAll();
        List productsList = ProductTable.extractAll(true, "roomNo", "asc");
        req.setAttribute("productList", productsList);
        req.setAttribute("applList", applList);
        req.setAttribute("orderList", orderList);

        if (req.getParameter("checkInProd")!= null && req.getParameter("checkOutProd")!=null) {
            for (Order order : orderList) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String date1 = df.format(order.getCheckIn());
                String date2 = df.format(order.getCheckOut());
                req.setAttribute("checkInProd", req.getParameter("checkInProd"));
                req.setAttribute("checkOutProd", req.getParameter("checkOutProd"));
                try {
                    if (DateUtils.datesOverlap(date1, date2,
                            req.getParameter("checkInProd"), req.getParameter("checkOutProd"))) {
                        req.setAttribute("datesCheck", "These dates are taken");
                    } else {
                        req.setAttribute("datesCheck", "its free!");
                    }
                } catch (ParseException e){
                    req.setAttribute("datesCheck", "please input correct dates");
                }
            }
        }
        return forward;

    }
}
