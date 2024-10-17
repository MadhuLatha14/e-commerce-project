package controller;

import model.Product;
import model.ProductDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/BuyerDashboardServlet")
public class BuyerDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve session
        HttpSession session = request.getSession(false); // Fetch existing session, don't create a new one

        // Check if the session exists and buyerId is available
        if (session != null && session.getAttribute("buyerId") != null) {
            int buyerId = (int) session.getAttribute("buyerId");

            // Fetch products using ProductDAO
            ProductDAO productDAO = new ProductDAO();
            List<Product> products = productDAO.getAllProducts();

            // Set products in request scope
            request.setAttribute("products", products);

            // Forward to buyer dashboard JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("buyer-dashboard.jsp");
            dispatcher.forward(request, response);
        } else {
            // If session or buyerId is null, redirect to login page with error message
            response.sendRedirect("login.jsp?error=Session expired. Please log in again.");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); // Reuse the same logic for POST requests
    }
}
