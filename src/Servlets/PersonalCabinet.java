package Servlets;

import MySQL.OrdersDB;
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
        List orderList = OrdersDB.getOrderByUserId(AppUtils.getLoginedUser(req.getSession()).getUserID());
        req.setAttribute("orderList", orderList);
        RequestDispatcher rq = req.getRequestDispatcher("/WEB-INF/Views/personalCabinet.jsp");
        rq.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}