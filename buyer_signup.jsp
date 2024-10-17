<!DOCTYPE html>
<html>
<head>
    <title>Buyer Sign Up</title>
    <link rel="stylesheet" href="Styles.css">
</head>
<body>
    <div class="container">
        <h2>Buyer Sign Up</h2>
        <form action="SignupServlet" method="post">
            <input type="hidden" name="role" value="buyer">
            <input type="text" name="username" placeholder="Enter Username" required><br>
            <input type="text" name="phone_no" placeholder="Enter Phone No" required><br>
            <input type="email" name="email" placeholder="Enter Email" required><br>
            <input type="text" name="address" placeholder="Enter Address" required><br>
            <input type="password" name="password" placeholder="Enter Password" required><br>
            <button type="submit">Register</button>
        </form>
    </div>
</body>
</html>
