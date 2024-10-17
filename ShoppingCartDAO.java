package model;

import utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ShoppingCartDAO {

    // Method to add an item to the cart
	public boolean addToCart(int prodId, int buyerId, int quantity, double price) {
        String sql = "INSERT INTO ShoppingCart (prod_id, buyer_id, quantity, price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the parameters for the prepared statement
            stmt.setInt(1, prodId);
            stmt.setInt(2, buyerId);
            stmt.setInt(3, quantity);
            stmt.setBigDecimal(4, new java.math.BigDecimal(price)); // Convert double to BigDecimal

            // Execute update and check if rows were inserted
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // Method to delete an item from the cart
    public boolean deleteFromCart(int productId, int buyerId) {
        String sql = "DELETE FROM ShoppingCart WHERE prod_id = ? AND buyer_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId); // Use productId instead of cartId for deletion
            stmt.setInt(2, buyerId); // Include buyerId to ensure the right cart item is deleted

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0; // Returns true if a product was deleted

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if there was an error
        }
    }
}
