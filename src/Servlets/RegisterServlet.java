package Servlets;


import JavaBeans.UserAccount;
import MySQL.UserDB;
import Utils.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static MySQL.UserDB.findByLogin;
import static MySQL.UserDB.insertUser;

@WebServlet("/register")
public class RegisterServlet extends HomeServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher //
                = request.getRequestDispatcher("/WEB-INF/Views/RegisterView.jsp");

        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userLogin = request.getParameter("userLogin");
        String password = request.getParameter("password");
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");

        if (userLogin.isEmpty() ||
                password.isEmpty() ||
                userName.isEmpty() ||
                email.isEmpty()) {

            String errorMessage = "Please fill in all required fields";
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/Views/RegisterView.jsp");
            rq.forward(request, response);
            return;
        }
//
        if (!insertUser(userLogin, userName, password, email)) {

            String message = "failed";
            RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/Views/RegisterView.jsp");
            request.setAttribute("errorMessage", message);
            rq.include(request, response);
            return;
        }
        String message = "Congrats! You have successfully signed up. Welcome to our family!\n" +
                "Please enter your login and pass to proceed to Your personal cabinet";
        RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/Views/loginView.jsp?message=" + message);
        request.setAttribute("welcomeMessage", message);
        rq.include(request, response);

    }
}
