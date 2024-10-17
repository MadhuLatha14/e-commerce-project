<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Product</title>
    
</head>
<body>

<h2>Add New Product</h2>
<form action="AddProductServlet" method="POST">
    <label for="prodName">Product Name:</label>
    <input type="text" id="prodName" name="prodName" required><br><br>

    <label for="prodDescription">Product Description:</label>
    <textarea id="prodDescription" name="prodDescription" required></textarea><br><br>

    <label for="price">Price:</label>
    <input type="number" id="price" name="price" step="0.01" required><br><br>

    <input type="submit" value="Add Product">
</form>

</body>
</html>
