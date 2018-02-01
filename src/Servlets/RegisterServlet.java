package Servlets;


import Utils.PasswordEncryption;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static MySQL.UserTable.insertUser;

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
        String password = null;
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        try {
            password = PasswordEncryption.getSaltedHash(request.getParameter("password"));
            System.out.println(password);
        } catch (Exception e) {
            e.printStackTrace();
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
