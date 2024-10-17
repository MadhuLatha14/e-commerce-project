package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductDAO;

@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the product ID to delete
        int productId = Integer.parseInt(request.getParameter("prod_id"));

        ProductDAO productDAO = new ProductDAO();
        
        // Call the deleteProduct method to remove the product from the database
        boolean isDeleted = productDAO.deleteProduct(productId);

        if (isDeleted) {
            // Redirect to a success page or back to the seller dashboard
            response.sendRedirect("seller-dashboard.jsp?message=Product deleted successfully");
        } else {
            // Handle error case
            request.setAttribute("error", "Product deletion failed.");
            request.getRequestDispatcher("seller-dashboard.jsp").forward(request, response);
        }
    }
}
