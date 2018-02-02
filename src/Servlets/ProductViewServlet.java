package Servlets;

import JavaBeans.Product;
import JavaBeans.UserAccount;
import MySQL.OrdersTable;
import MySQL.ProductTable;
import Utils.AppUtils;

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
        String orderBy = request.getParameter("orderBy");
        String order = request.getParameter("order");
        if (orderBy == null) {
            orderBy = "roomNo";
        }
        if (order == null) {
            order = "asc";
        }
        productList = ProductTable.extractAll(false, orderBy, order);
        request.setAttribute("productList", productList);
        System.out.println("doGet");
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
        int productId = Integer.parseInt(req.getParameter("prodId"));
        String checkIn = req.getParameter("checkIn");
        String checkOut = req.getParameter("checkOut");
        String appId = req.getParameter("appId");

        if (ProductTable.isTakenById(productId, checkIn, checkOut)) {
            System.out.println("isTaken");
            req.setAttribute("message", "taken for this dates");
            doGet(req, resp);
        } else {
            if (OrdersTable.insertOrder(userId, productId, checkIn, checkOut, appId)) {
                System.out.println("insert order");
                req.setAttribute("message", "You have successfully made an order.");
                doGet(req, resp);
            } else {
                req.setAttribute("offerMessage", "Cant insert order.");
                RequestDispatcher rq = req.getRequestDispatcher("personalCabinet");
                rq.forward(req, resp);

            }
        }


    }
}
