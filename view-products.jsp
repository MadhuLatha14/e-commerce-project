<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Available Products</title>
</head>
<body>
    <h1>Available Products</h1>

    <c:if test="${not empty productList}">
        <table border="1">
            <tr>
                <th>Product Name</th>
                <th>Description</th>
                <th>Price</th>
            </tr>
            <c:forEach var="product" items="${productList}">
                <tr>
                    <td>${product.prodName}</td>
                    <td>${product.productDescription}</td>
                    <td>${product.price}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty productList}">
        <p>No products available at the moment.</p>
    </c:if>

</body>
</html>
