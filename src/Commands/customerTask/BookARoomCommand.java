package Commands.customerTask;


import Commands.Attributes;
import Commands.Messages;
import Commands.Paths;
import MySQL.Fields;
import MySQL.JavaBeans.UserAccount;
import MySQL.tables.OrdersTable;
import MySQL.tables.ProductTable;
import Utils.AppUtils;
import Utils.EmailUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

public class BookARoomCommand extends Commands.Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserAccount user = AppUtils.getLoginedUser(request.getSession());

        if (user == null) {
            request.setAttribute("errorMessage", "sorry you have to login");
            return "/Views/loginView.jsp";
        }

        int userId = AppUtils.getLoginedUser(request.getSession()).getUserID();
        int productId = Integer.parseInt(request.getParameter(Attributes.PRODUCT_ID));
        String checkIn = request.getParameter(Fields.CHECK_IN);
        String checkOut = request.getParameter(Fields.CHECK_OUT);
        String appId = request.getParameter(Attributes.APP_ID);
        String reqFrom = request.getParameter(Attributes.REQUEST_FROM);
        String radioBuyCheck = request.getParameter(Attributes.BUY);
        String forward = Paths.COMMAND_EMPTY + reqFrom;

        try {
            if (ProductTable.isTakenById(productId, checkIn, checkOut)) {
                request.getSession().setAttribute(Attributes.MESSAGE, Messages.IS_TAKEN);
            } else {
                boolean payItUp = radioBuyCheck!=null;
                if (OrdersTable.insertOrder(userId, productId, checkIn, checkOut, appId, payItUp)) {
                    ProductTable.setTakenOrFree(productId, 1);
                    request.getSession().setAttribute(Attributes.MESSAGE, Messages.SUCCESSFUL_ORDER);
                } else {
                    request.setAttribute(Attributes.MESSAGE, Messages.CANT_INSERT_ORDER);
                }
            }
        } catch (ParseException | NullPointerException e) {
            e.printStackTrace();
            request.getSession().setAttribute(Attributes.MESSAGE, Messages.DATE_FIELDS_ERROR);
        }
        String emailMessage = "Dear " + user.getUserName() + "!\n" + "You have reserved a room in GG Hotel: \n" +
                "Application # " + appId + "\n" +
        "Your room #is " + productId + "\n" +
        "Check In date " + checkIn + "\n" +
        "Check Out date " + checkOut +  "\n" +
                "In order to confirm Your application please login to dashboard and make a payment. \n" +
                "Please remember You have to confirm you order before your check in date \n" +
                "Have a nice day!" +
                "GG Team";

        EmailUtils.send(user.getEmail(), "GG Hotel Room Reservation", emailMessage);
        return forward;
    }
}
