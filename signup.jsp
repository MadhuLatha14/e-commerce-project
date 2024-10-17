<!DOCTYPE html>
<html>
<head>
    <title>Sign Up</title>
    <link rel="stylesheet" href="Styles.css">
    <style>
        .button-container {
            margin: 50px auto;
        }
        .role-button {
            display: inline-block;
            width: 150px;
            padding: 15px;
            margin: 10px;
            background-color: #ff5722;
            color: white;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .role-button:hover {
            background-color: #e64a19;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Select User Type</h2>
        <div class="button-container">
            <a class="role-button" href="buyer_signup.jsp">Buyer</a>
            <a class="role-button" href="seller_signup.jsp">Seller</a>
        </div>
    </div>
</body>
</html>
