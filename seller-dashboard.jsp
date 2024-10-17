<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Seller Dashboard</title>
    <link rel="stylesheet" href="Styles.css">
</head>
<body>
    <h1>Welcome, ${username}!</h1>
    
    <!-- Link for Add Product -->
   
	<form action="add-product.jsp" method="get">
        <input type="submit" value="Add Product">
    </form>
    <!-- Existing form buttons -->
    
    <form action="delete-product.jsp" method="get">
        <input type="submit" value="Delete Product">
    </form>
    <form action="ViewProductsServlet" method="get">
        <input type="submit" value="View Products">
    </form>
</body>
</html>
