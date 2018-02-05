package Commands.customerTask;

import Commands.Command;
import JavaBeans.UserAccount;
import MySQL.OrdersTable;
import MySQL.ProductTable;
import Utils.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

public class BookARoomCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserAccount user = AppUtils.getLoginedUser(request.getSession());

        if (user == null) {
            request.setAttribute("errorMessage", "sorry you have to login");
            return "/Views/loginView.jsp";
        }

        int userId = AppUtils.getLoginedUser(request.getSession()).getUserID();
        int productId = Integer.parseInt(request.getParameter("prodId"));
        String checkIn = request.getParameter("checkIn");
        String checkOut = request.getParameter("checkOut");
        String appId = request.getParameter("appId");
        String reqFrom = request.getParameter("reqFrom");
        String forward = "/controller?command=" + reqFrom;

        try {
            if (ProductTable.isTakenById(productId, checkIn, checkOut)) {
                request.setAttribute("message", "taken for this dates");

            } else {
                if (OrdersTable.insertOrder(userId, productId, checkIn, checkOut, appId)) {
                    ProductTable.setTakenOrFree(productId, 1);
                    request.setAttribute("message", "You have successfully made an order.");
                } else {
                    request.setAttribute("message", "Cant insert order. Please check if you dates are correct");
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("message", "Please fill desired dates fields.");
        }
        return forward;
    }
}
