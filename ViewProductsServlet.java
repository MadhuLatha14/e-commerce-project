package controller;

import model.Product;
import model.ProductDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ViewProductsServlet")
public class ViewProductsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO productDAO = new ProductDAO();
        List<Product> productList = productDAO.getAllProducts();

        request.setAttribute("productList", productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view-products.jsp");
        dispatcher.forward(request, response);
    }
}
