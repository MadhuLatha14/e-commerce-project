<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Delete Product</title>
</head>
<body>
    <h1>Delete Product</h1>
    <form action="DeleteProductServlet" method="POST">
        <label for="prod_id">Product ID:</label>
        <input type="number" id="prod_id" name="prod_id" required>
        <input type="submit" value="Delete Product">
    </form>
    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>
    <c:if test="${not empty param.message}">
        <p style="color:green;">${param.message}</p>
    </c:if>
</body>
</html>
