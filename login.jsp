<!-- login.jsp -->
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="Styles.css">
</head>
<body>
    <div class="container">
        <h2>Login</h2>

        <!-- Show error message if login failed -->
        <c:if test="${not empty error}">
            <p style="color:red;">${error}</p>
        </c:if>

        <!-- Login form -->
        <form action="LoginServlet" method="post">
            <input type="text" name="username" placeholder="Enter Username"
                value="${username != null ? username : ''}" required><br>
            <input type="password" name="password" placeholder="Enter Password"
                value="${password != null ? password : ''}" required><br>
            <button type="submit">Login</button>
        </form>
    </div>
</body>
</html>
