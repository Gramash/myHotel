package Servlets;

import JavaBeans.Application;
import JavaBeans.Order;
import JavaBeans.Product;
import MySQL.ApplicationsTable;
import MySQL.OrdersTable;
import MySQL.ProductTable;
import Utils.AppUtils;
import com.sun.org.apache.xpath.internal.operations.Or;
import sun.rmi.server.InactiveGroupException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/filterDB")
public class ResultsForApplicationsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = req.getParameter("sleeps");
        String checkIn = req.getParameter("checkIn");
        String checkOut = req.getParameter("checkOut");
        String userId = req.getParameter("userID");
        String clazz = req.getParameter("class");
        List<Order> orderList = OrdersTable.getOrderManager();
        List<Product> listSuggestions = ProductTable.selectSuitable(Integer.parseInt(str), checkIn, checkOut, clazz);
        List<Application> appList = ApplicationsTable.extractAll();
        req.setAttribute("orderList", orderList);
        req.setAttribute("applList", appList);
        req.setAttribute("productList", listSuggestions);
        req.setAttribute("appId", req.getParameter("appId"));
        req.setAttribute("user_id", userId);
        if(AppUtils.getLoginedUser(req.getSession())==null){
            RequestDispatcher rq = req.getRequestDispatcher("/WEB-INF/Views/loginView.jsp");

            rq.forward(req, resp);
        }
        if (req.getParameter("appId")==null) {
            RequestDispatcher rq = req.getRequestDispatcher("/WEB-INF/Views/ProductView.jsp");

            rq.forward(req, resp);
        } else {
            RequestDispatcher rq = req.getRequestDispatcher("/WEB-INF/Views/managerTaskView.jsp");

            rq.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
