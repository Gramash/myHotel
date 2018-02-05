package Commands.managerTask;

import Commands.Command;
import MySQL.ProductTable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateProductCommand extends Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("UpdateProductCommand");
        int roomNo = Integer.parseInt(req.getParameter("roomNo"));
        int sleeps = Integer.parseInt(req.getParameter("sleeps"));
        double price = Double.parseDouble(req.getParameter("price"));
        boolean available = Boolean.parseBoolean(req.getParameter("available"));
        String clazz = req.getParameter("class");
        ProductTable.updateProduct(roomNo, sleeps,price, available, clazz);
        System.out.println(roomNo + " " + sleeps + " " + price + " " + available);
        return "/controller?command=managerTask";
    }
}
