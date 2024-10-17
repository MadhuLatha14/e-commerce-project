<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Product</title>
</head>
<body>

<h2>Edit Product</h2>

<form action="UpdateProductServlet" method="POST">
    <input type="hidden" name="prodId" value="${product.prodId}"/> <!-- Make sure to set this -->
    <label for="productName">Product Name:</label>
    <input type="text" id="productName" name="prodName" value="${product.prodName}" required><br><br>

    <label for="productDescription">Product Description:</label>
    <textarea id="productDescription" name="productDescription" required>${product.productDescription}</textarea><br><br>

    <label for="price">Price:</label>
    <input type="number" id="price" name="price" value="${product.price}" step="0.01" required><br><br>

    <label for="quantity">Quantity:</label>
    <input type="number" id="quantity" name="quantity" value="${product.quantity}" required><br><br>

    <input type="submit" value="Update Product">
</form>


</body>
</html>
