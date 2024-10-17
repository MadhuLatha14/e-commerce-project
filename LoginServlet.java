package controller;

import model.User;
import model.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve username and password from request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Authenticate user using UserDAO
        UserDAO userDAO = new UserDAO();
        User user = userDAO.authenticateUser(username, password); // Ensure this method correctly validates role and credentials

        if (user != null) {
            // If user is authenticated, create a session and store user info
            HttpSession session = request.getSession(true);  // Create a new session if one doesn't exist
            session.setAttribute("user", user);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole()); // Store user role (buyer or seller)

            if ("buyer".equals(user.getRole())) {
                session.setAttribute("buyerId", user.getId());  // Store buyerId for buyers
                response.sendRedirect("BuyerDashboardServlet");
            } else if ("seller".equals(user.getRole())) {
                session.setAttribute("sellerId", user.getId());  // Store sellerId for sellers
                response.sendRedirect("SellerDashboardServlet");
            } else {
                // If the role is neither buyer nor seller
                request.setAttribute("error", "Invalid user role.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            // If authentication fails, redirect back to the login page with an error message
            request.setAttribute("error", "Invalid credentials.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
