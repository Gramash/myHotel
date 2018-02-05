package Commands.customerTask;

import Commands.Command;
import MySQL.OrdersTable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class ConfirmOrder extends Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        int productId = Integer.parseInt(req.getParameter("prodId"));
        Date checkIn = Date.valueOf(req.getParameter("checkIn"));
        Date checkOut = Date.valueOf(req.getParameter("checkOut"));
        System.out.println(productId + " " + checkIn + " " + checkOut);
        OrdersTable.confirmOrder(productId, checkIn, checkOut);
        return "controller?command=dashboard";
    }
}
