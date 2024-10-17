package controller;

import model.ShoppingCartDAO;
import model.ProductDAO; // Import ProductDAO to fetch product details
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve product ID and quantity from the request
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Get buyerId from session
        HttpSession session = request.getSession();
        Integer buyerId = (Integer) session.getAttribute("buyerId");

        // Handle case where buyerId is not found
        if (buyerId == null) {
            response.sendRedirect("login.jsp?error=Session expired. Please log in again.");
            return;
        }

        // Fetch product price using ProductDAO
        ProductDAO productDAO = new ProductDAO();
        double price = productDAO.getProductPriceById(productId); // Ensure this method returns the correct price

        // Add product to cart using ShoppingCartDAO
        ShoppingCartDAO cartDAO = new ShoppingCartDAO();
        boolean isAdded = cartDAO.addToCart(productId, buyerId, quantity, price);

        // Redirect based on success or failure
        if (isAdded) {
            response.sendRedirect("buyer-dashboard.jsp?message=Item added to cart");
        } else {
            response.sendRedirect("buyer-dashboard.jsp?error=Failed to add item to cart");
        }
    }
}
