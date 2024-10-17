package controller;

import model.Product;
import model.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/SellerDashboardServlet")
public class SellerDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check session validity
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("sellerId") == null) {
            response.sendRedirect("login.jsp?error=Session expired. Please log in again.");
            return;
        }

        // Get sellerId from session
        int sellerId = (int) session.getAttribute("sellerId");

        // Fetch products by sellerId
        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.getProductsBySellerId(sellerId);

        // Set products in request and forward to seller dashboard
        request.setAttribute("products", products);
        request.getRequestDispatcher("seller-dashboard.jsp").forward(request, response);
    }
}

