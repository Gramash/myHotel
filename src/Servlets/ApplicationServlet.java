package Servlets;

import JavaBeans.UserAccount;
import MySQL.ApplicationsTable;
import Utils.AppUtils;
import com.sun.org.apache.xpath.internal.SourceTree;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/applicationForm")
public class ApplicationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserAccount user = AppUtils.getLoginedUser(req.getSession());
        String message = null;
        RequestDispatcher rq;
        try {
            if(!ApplicationsTable.insertApplication(user.getUserID(), Integer.parseInt(req.getParameter("sleeps")),
                    req.getParameter("checkIn"), req.getParameter("checkOut"))){
                message = "Please specify correct date range";
                req.setAttribute("message", message);
                rq = req.getRequestDispatcher("/WEB-INF/Views/homeView.jsp");
                rq.forward(req, resp);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            message = "Sorry, You have to login prior to making applications";
            req.setAttribute("message", message);
            rq = req.getRequestDispatcher("/WEB-INF/Views/homeView.jsp");
            rq.forward(req, resp);
        }
        message = "You have successfully made an application. Please visit Your Dashboard to check for an offer";
        req.setAttribute("message", message);
        rq = req.getRequestDispatcher("/WEB-INF/Views/homeView.jsp");
        rq.forward(req, resp);

    }
}
