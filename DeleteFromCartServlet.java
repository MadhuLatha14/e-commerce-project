package controller;

import model.ShoppingCartDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/DeleteFromCartServlet")
public class DeleteFromCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read the productId parameter from the request
        String productIdStr = request.getParameter("productId");

        if (productIdStr != null && !productIdStr.isEmpty()) {
            try {
                int productId = Integer.parseInt(productIdStr);

                // Get buyerId from session
                HttpSession session = request.getSession();
                int buyerId = (int) session.getAttribute("buyerId");

                // Create ShoppingCartDAO and delete item
                ShoppingCartDAO cartDAO = new ShoppingCartDAO();
                boolean isDeleted = cartDAO.deleteFromCart(productId, buyerId); // Assuming method signature

                if (isDeleted) {
                    response.sendRedirect("buyer-dashboard.jsp?message=Item removed from cart");
                } else {
                    response.sendRedirect("buyer-dashboard.jsp?error=Failed to remove item from cart");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("buyer-dashboard.jsp?error=Invalid product ID");
            }
        } else {
            response.sendRedirect("buyer-dashboard.jsp?error=Product ID not found");
        }
    }
}
