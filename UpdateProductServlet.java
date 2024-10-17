package controller;

import model.Product;
import model.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UpdateProductServlet")
public class UpdateProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String prodIdStr = request.getParameter("prodId");
        String prodName = request.getParameter("prodName");
        String productDescription = request.getParameter("productDescription");
        String priceStr = request.getParameter("price");
        String quantityStr = request.getParameter("quantity"); // Assuming you have a quantity field for updating

        // Validate required fields
        if (prodIdStr == null || prodIdStr.trim().isEmpty() ||
            prodName == null || prodName.trim().isEmpty() ||
            priceStr == null || priceStr.trim().isEmpty()) {

            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("edit-product.jsp").forward(request, response);
            return;
        }

        int prodId;
        double price;
        int quantity;

        // Parse product ID and price safely
        try {
            prodId = Integer.parseInt(prodIdStr);
            price = Double.parseDouble(priceStr);
            quantity = Integer.parseInt(quantityStr); // If you are updating quantity
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid input. Please enter numeric values for ID, price, and quantity.");
            request.getRequestDispatcher("edit-product.jsp").forward(request, response);
            return;
        }

        // Create Product object
        Product product = new Product();
        product.setProdId(prodId);
        product.setProdName(prodName);
        product.setProductDescription(productDescription);
        product.setPrice(price);
        product.setQuantity(quantity); // Set quantity if applicable

        // Update product in the database
        ProductDAO productDAO = new ProductDAO();
        boolean isUpdated = productDAO.updateProduct(product); // Ensure you have this method in ProductDAO

        if (isUpdated) {
            response.sendRedirect("SellerDashboardServlet");
        } else {
            request.setAttribute("error", "Error updating product. Please try again.");
            request.getRequestDispatcher("edit-product.jsp").forward(request, response);
        }
    }
}
