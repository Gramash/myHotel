package Commands.managerTask;

import Commands.Command;
import Commands.Paths;
import MySQL.Fields;
import MySQL.tables.ProductTable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateProductCommand extends Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int roomNo = Integer.parseInt(req.getParameter(Fields.ROOM_NO));
        int sleeps = Integer.parseInt(req.getParameter(Fields.SLEEPS));
        double price = Double.parseDouble(req.getParameter(Fields.PRICE));
        boolean available = Boolean.parseBoolean(req.getParameter(Fields.AVAILABLE));
        String clazz = req.getParameter(Fields.CLASS);
        ProductTable.updateProduct(roomNo, sleeps,price, available, clazz);
        return Paths.COMMAND_MANAGER;
    }
}
