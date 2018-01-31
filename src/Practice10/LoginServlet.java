package Practice10;

import JavaBeans.UserAccount;
import MySQL.UserTable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login1")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher //
                = request.getRequestDispatcher("/Part3/login.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userLogin = request.getParameter("userLogin");
        String password = request.getParameter("password");

        UserAccount userAccount = UserTable.findByLogin(userLogin, password);

        if (userAccount == null) {

            RequestDispatcher dispatcher //
                    = request.getRequestDispatcher("Part3/login.jsp");

            dispatcher.forward(request, response);
            return;
        }



        response.sendRedirect(request.getContextPath() + "/part3");


    }

}