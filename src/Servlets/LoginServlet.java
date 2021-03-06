package Servlets;

import JavaBeans.UserAccount;
import MySQL.ConnectionUtils;
import MySQL.UserTable;
import Utils.AppUtils;
import Utils.EmailUtils;
import config.SecurityConfig;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.Authenticator;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

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

        UserAccount userAccount = UserTable.findByLogin(userLogin, password);

        if (userAccount == null) {
            String errorMessage = "Invalid Login or Password";

            request.setAttribute("errorMessage", errorMessage);

            RequestDispatcher dispatcher //
                    = request.getRequestDispatcher("/WEB-INF/Views/loginView.jsp");

            dispatcher.forward(request, response);
            return;
        }

        AppUtils.storeLoginedUser(request.getSession(), userAccount);
        request.getSession().setAttribute("role", SecurityConfig.ROLE_MANAGER);


        response.sendRedirect(request.getContextPath() + "/userInfo");


    }

}
