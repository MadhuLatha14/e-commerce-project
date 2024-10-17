package controller;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.UserDAO;
import model.User;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phoneNo = request.getParameter("phone_no");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String role = request.getParameter("role"); // buyer or seller

        // Debugging logs
        

        // Create a new User object
        User newUser = new User(username, phoneNo, email, address, password, role);

        // Call UserDAO to save the user
        UserDAO userDao = new UserDAO();
        boolean isUserSaved = userDao.saveUser(newUser);

        if (isUserSaved) {
            // Redirect to login page after successful signup
            response.sendRedirect("login.jsp");
        } else {
            // Log the error for debugging
            
            // Handle error case (e.g., username already exists)
            request.setAttribute("error", "Signup failed. Try a different username.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
            dispatcher.forward(request, response);
        }
    }
}
