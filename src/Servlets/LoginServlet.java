package Servlets;

import JavaBeans.UserAccount;
import MySQL.UserDB;
import Utils.AppUtils;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher //
                = request.getRequestDispatcher("/WEB-INF/Views/loginView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userLogin = request.getParameter("userLogin");
        String password = request.getParameter("password");

        UserAccount userAccount = UserDB.findByLogin(userLogin, password);

        if (userAccount == null) {
            String errorMessage = "Invalid Login or Password";

            request.setAttribute("errorMessage", errorMessage);

            RequestDispatcher dispatcher //
                    = request.getRequestDispatcher("/WEB-INF/Views/loginView.jsp");

            dispatcher.forward(request, response);
            return;
        }

        AppUtils.storeLoginedUser(request.getSession(), userAccount);


        // redirect to userInfo after succesessful login
        response.sendRedirect(request.getContextPath() + "/userInfo");


    }

}
