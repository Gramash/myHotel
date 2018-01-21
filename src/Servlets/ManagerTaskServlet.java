package Servlets;

import JavaBeans.Order;
import MySQL.OrdersDB;
import config.SecurityConfig;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/managerTask")
public class ManagerTaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orderList;
        orderList = OrdersDB.getOrderManager();
        for(Order order : orderList){
            System.out.println(order);
        }
        req.setAttribute("orderList", orderList);
        RequestDispatcher rq = req.getRequestDispatcher("/WEB-INF/Views/managerTaskView.jsp");
        rq.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
