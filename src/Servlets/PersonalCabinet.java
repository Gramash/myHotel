package Servlets;

import JavaBeans.Application;
import MySQL.ApplicationResponseTable;
import MySQL.ApplicationsTable;
import MySQL.OrdersTable;
import Utils.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/personalCabinet")
public class PersonalCabinet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            System.out.println("PersonalCabinet#doGet");
            List orderList = OrdersTable.getOrderByUserId(AppUtils.getLoginedUser(req.getSession()).getUserID());
            List applList = ApplicationsTable.extractForUser(AppUtils.getLoginedUser(req.getSession()).getUserID());
            List offerList = ApplicationResponseTable.getResponseForUser(AppUtils.getLoginedUser(req.getSession()).getUserID());
            System.out.println(offerList);
            req.setAttribute("orderList", orderList);
            req.setAttribute("applList", applList);
            req.setAttribute("offerList", offerList);
            if (orderList.size() == 0) {
                req.setAttribute("message", "You have no pending orders yet" + "\n Proceed products to make an order");
            }
            if (applList.size() == 0) {
                req.setAttribute("applMessage", "You have no applications yet");
            }
            if (offerList.size() == 0) {
                req.setAttribute("offerMessage", "You have no offers for your applications");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            RequestDispatcher rq = req.getRequestDispatcher("/WEB-INF/Views/personalCabinet.jsp");
            rq.forward(req, resp);
        }
        RequestDispatcher rq = req.getRequestDispatcher("/WEB-INF/Views/personalCabinet.jsp");
        rq.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}