package model;

import java.math.BigDecimal;

public class Product {
    private int prodId;
    private int sellerId;
    private String prodName;
    private String productDescription;
    private BigDecimal price;
    private int quantity; // Add quantity field

    public Product() {
    }

    // Constructor
    public Product(int sellerId, String prodName, String productDescription, BigDecimal price, int quantity) {
        this.sellerId = sellerId;
        this.prodName = prodName;
        this.productDescription = productDescription;
        this.price = price;
        this.quantity = quantity; // Set quantity
    }

    // Getters and Setters
    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    // Convert double to BigDecimal for setting price
    public void setPrice(double price) {
        this.price = BigDecimal.valueOf(price);
    }

    public int getQuantity() { // Add getter for quantity
        return quantity;
    }

    public void setQuantity(int quantity) { // Add setter for quantity
        this.quantity = quantity;
    }
}
