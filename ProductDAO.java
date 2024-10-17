package model;

import utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class ProductDAO {

    // Method to add a product (seller)
    public boolean addProduct(Product product) {
        String sql = "INSERT INTO Product (seller_id, prod_name, product_description, price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, product.getSellerId());
            stmt.setString(2, product.getProdName());
            stmt.setString(3, product.getProductDescription());
            stmt.setBigDecimal(4, product.getPrice());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Error adding product: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete a product (seller)
    public boolean deleteProduct(int productId) {
        String sql = "DELETE FROM Product WHERE prod_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateProduct(Product product) {
        String sql = "UPDATE Product SET prodName = ?, productDescription = ?, price = ?, quantity = ? WHERE prod_id = ?";

        try (Connection connection = DBConnection.getConnection(); // Ensure this method connects to your DB
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            statement.setString(1, product.getProdName());
            statement.setString(2, product.getProductDescription());
            statement.setBigDecimal(3, product.getPrice());
            statement.setInt(4, product.getQuantity()); // Set the quantity
            statement.setInt(5, product.getProdId());

            int rowsAffected = statement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
            return rowsAffected > 0; // Return true if the update was successful
        } catch (SQLException e) {
            e.printStackTrace(); // Print exception for debugging
            return false;
        }
    }
    // Method to fetch all products for buyers
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Product product = new Product();
                product.setProdId(rs.getInt("prod_id"));
                product.setSellerId(rs.getInt("seller_id"));
                product.setProdName(rs.getString("prod_name"));
                product.setProductDescription(rs.getString("product_description"));
                product.setPrice(rs.getBigDecimal("price"));

                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Method to get the product price by ID
    public double getProductPriceById(int productId) {
        String sql = "SELECT price FROM Product WHERE prod_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Return the price as a double
                return rs.getBigDecimal("price").doubleValue();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0; // Return 0.0 if product is not found or an error occurs
    }

    // Method to get products by seller ID
    public List<Product> getProductsBySellerId(int sellerId) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE seller_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, sellerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProdId(rs.getInt("prod_id"));
                product.setSellerId(rs.getInt("seller_id"));
                product.setProdName(rs.getString("prod_name"));
                product.setProductDescription(rs.getString("product_description"));
                product.setPrice(rs.getBigDecimal("price"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products; // Return the list of products for the given seller ID
    }
}
