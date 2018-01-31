package Practice10;

import JavaBeans.UserAccount;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/part3")
public class Part3Servlet extends HttpServlet {
    List<String> list = new ArrayList<>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher //
                = req.getRequestDispatcher("/Part3/Part3.jsp");

        dispatcher.forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        list.clear();
        String user = req.getParameter("userLogin");
        if(user.length()!=0){
            list.add(user);
        }
        Utils.storeUserList(req.getSession(), list);
        resp.sendRedirect(req.getContextPath() + "/part3");

    }


}
