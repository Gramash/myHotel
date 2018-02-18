package Commands.customerTask;

import Commands.Attributes;
import Commands.Command;
import Commands.Paths;
import MySQL.Fields;
import MySQL.tables.OrdersTable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class ConfirmOrder extends Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        int productId = Integer.parseInt(req.getParameter(Attributes.PRODUCT_ID));
        Date checkIn = Date.valueOf(req.getParameter(Fields.CHECK_IN));
        Date checkOut = Date.valueOf(req.getParameter(Fields.CHECK_OUT));
        System.out.println(productId + " " + checkIn + " " + checkOut);
        OrdersTable.confirmOrder(productId, checkIn, checkOut);
        return Paths.COMMAND_DASHBOARD;
    }
}
