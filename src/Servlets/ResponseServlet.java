package Servlets;

import JavaBeans.ApplicationResponse;
import MySQL.ApplicationResponseTable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/response")
public class ResponseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath()+"/managerTask");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("userId"));
        System.out.println(req.getParameter("appId"));
        System.out.println(req.getParameter("roomNo"));
        if (ApplicationResponseTable.insertResponse(req.getParameter("appId"),
                Integer.parseInt(req.getParameter("userId")), Integer.parseInt(req.getParameter("roomNo")))){
            System.out.println("response sent");
        }
        doGet(req,resp);
    }
}
