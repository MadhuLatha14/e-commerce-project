package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBConnection;

public class UserDAO {

    // Method to save user in the database
    public boolean saveUser(User user) {
        String sql = "INSERT INTO User (name, phone_no, email_address, address, password, role) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPhoneNo());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getAddress());
            stmt.setString(5, user.getPassword()); // Ensure to hash password before saving
            stmt.setString(6, user.getRole());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Updated authenticateUser method to return a User object
    public User authenticateUser(String username, String password) {
        User user = null;
        String sql = "SELECT * FROM User WHERE name = ?"; // Don't check password in the query yet

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Create a User object and populate it with data from the result set
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("name"));
                user.setPhoneNo(rs.getString("phone_no"));
                user.setEmail(rs.getString("email_address"));
                user.setAddress(rs.getString("address"));
                user.setPassword(rs.getString("password")); // Store hashed password
                user.setRole(rs.getString("role"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Now verify the password if user was found
        if (user != null && !user.getPassword().equals(password)) {
            return null; // Return null if password doesn't match
        }

        return user; // Return the user object (or null if no match)
    }
}
