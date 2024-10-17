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
import java.math.BigDecimal;

@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check session validity
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("sellerId") == null) {
            response.sendRedirect("login.jsp?error=Session expired. Please log in again.");
            return;
        }

        // Get sellerId from session
        int sellerId = (int) session.getAttribute("sellerId");

        // Retrieve product details from request (form field names must match)
        String prodName = request.getParameter("prodName");
        String prodDescription = request.getParameter("prodDescription");
        String priceStr = request.getParameter("price");

        // Validate required fields
        if (prodName == null || prodName.trim().isEmpty()) {
            request.setAttribute("error", "Product name cannot be empty.");
            request.getRequestDispatcher("add-product.jsp").forward(request, response);
            return;
        }
        if (priceStr == null || priceStr.trim().isEmpty()) {
            request.setAttribute("error", "Product price cannot be empty.");
            request.getRequestDispatcher("add-product.jsp").forward(request, response);
            return;
        }

        // Parse price as BigDecimal
        BigDecimal price = new BigDecimal(priceStr);

        // Create Product object
        Product product = new Product();
        product.setSellerId(sellerId);
        product.setProdName(prodName);
        product.setProductDescription(prodDescription);
        product.setPrice(price);

        // Insert product into database using ProductDAO
        ProductDAO productDAO = new ProductDAO();
        boolean isAdded = productDAO.addProduct(product);

        if (isAdded) {
            // Redirect to seller dashboard on success
            response.sendRedirect("SellerDashboardServlet");
        } else {
            // Display error if product could not be added
            request.setAttribute("error", "Error adding product. Please try again.");
            request.getRequestDispatcher("add-product.jsp").forward(request, response);
        }
    }
}
