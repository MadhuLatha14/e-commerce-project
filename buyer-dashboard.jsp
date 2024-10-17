<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Buyer Dashboard</title>
</head>
<body>
    <h1>Welcome, ${username}!</h1>

    <!-- View Products Button -->
    <a href="ViewProductsServlet">View Products</a><br><br>

    <h2>Available Products</h2>
    <c:forEach var="product" items="${products}">
        <div>
            <h3>${product.prodName}</h3>
            <p>${product.productDescription}</p>
            <p>Price: ${product.price}</p>

            <!-- Add to Cart Form -->
            <form action="AddToCartServlet" method="post">
                <input type="hidden" name="productId" value="${product.prodId}">
                <input type="hidden" name="productName" value="${product.prodName}"> <!-- Retain product name -->
                Quantity: <input type="number" name="quantity" value="1" min="1">
                <input type="submit" value="Add to Cart">
            </form>

            <!-- Delete from Cart Form -->
            <form action="DeleteFromCartServlet" method="post">
                <input type="hidden" name="productId" value="${product.prodId}"> <!-- Keep productId for deletion -->
                <!-- buyerId can be retrieved from session directly in the servlet -->
                <input type="submit" value="Remove from Cart"> <!-- Remove extra hidden field -->
            </form>
        </div>
        <br>
    </c:forEach>
</body>
</html>
