package Servlets;


import JavaBeans.Order;
import MySQL.ApplicationsTable;
import MySQL.OrdersTable;
import MySQL.ProductTable;
import Utils.DateUtils;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/managerTask")
public class ManagerTaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("managerTask#doget");
        List<Order> orderList;
        orderList = OrdersTable.getOrderManager();
        List applList = ApplicationsTable.extractAll();
        List productsList = ProductTable.extractAll(true, "roomNo", "asc");
        req.setAttribute("productList", productsList);
        req.setAttribute("applList", applList);
        req.setAttribute("orderList", orderList);
        RequestDispatcher rq = req.getRequestDispatcher("/WEB-INF/Views/managerTaskView.jsp");
        rq.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orderList = OrdersTable.getOrderManager();
        for (Order order : orderList) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = df.format(order.getCheckIn());
            String date2 = df.format(order.getCheckOut());
            req.setAttribute("checkIn", req.getParameter("checkIn"));
            req.setAttribute("checkOut", req.getParameter("checkOut"));
            if (DateUtils.datesOverlap(date1, date2,
                    req.getParameter("checkIn"), req.getParameter("checkOut"))) {
                req.setAttribute("datesCheck", "These dates are taken");
            } else {
                req.setAttribute("datesCheck", "its free!");
            }
        }
        doGet(req, resp);
    }
}
