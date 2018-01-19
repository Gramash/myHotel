package Servlets;

import JavaBeans.Product;
import JavaBeans.UserAccount;
import MySQL.OrdersDB;
import MySQL.ProductDB;
import Utils.AppUtils;
import Utils.UserUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/productView")
public class ProductViewServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList;
        productList = ProductDB.extractAll();
        request.setAttribute("productList", productList);

        RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/Views/ProductView.jsp");
        rq.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserAccount user = AppUtils.getLoginedUser(req.getSession());

        if (user == null) {
            RequestDispatcher dispatcher //
                    = req.getRequestDispatcher("/WEB-INF/Views/loginView.jsp");

            dispatcher.forward(req, resp);
        }

        int userId = AppUtils.getLoginedUser(req.getSession()).getUserID();
        System.out.println(userId);
        int productId = Integer.parseInt(req.getParameter("id"));
        System.out.println(productId);
        String checkIn = UserUtils.formatDate(req.getParameter("checkIn"), "mm/dd/yyyy", "yyyy/mm/dd");
        String checkOut = UserUtils.formatDate(req.getParameter("checkOut"), "mm/dd/yyyy", "yyyy/mm/dd");

        if (OrdersDB.insertOrder(userId, productId, checkIn, checkOut)) {


        }
        req.setAttribute("confirmMessage", "LOL");
        doGet(req, resp);
    }
}
