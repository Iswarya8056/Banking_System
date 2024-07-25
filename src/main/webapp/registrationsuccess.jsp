<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bankmodel.user" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Success</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .success-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 400px;
            text-align: center;
        }
        .success-container h2 {
            color: #4CAF50; /* Green color for the heading */
            margin-bottom: 20px;
        }
        .success-container p {
            margin: 10px 0;
            color: #333; /* Dark color for text */
        }
        .success-container strong {
            color: #4CAF50; /* Green color for labels */
        }
        .go-back {
            display: block;
            margin-top: 20px;
            font-size: 16px;
            color: #4CAF50;
            text-decoration: none;
            border: 1px solid #4CAF50;
            padding: 10px 20px;
            border-radius: 5px;
            width: 150px;
            text-align: center;
            margin: 20px auto;
        }
        .go-back:hover {
            background-color: #4CAF50;
            color: #fff;
        }
    </style>
</head>
<body>
    <div class="success-container">
        <h2>Registration Successful</h2>
        <p>Account successfully saved.</p>
        <%
            user user = (user) session.getAttribute("user");
            if (user != null) {
        %>
        <p><strong>Account Number:</strong> <%= user.getAccountNumber() %></p>
        <p><strong>Temporary Password:</strong> <%= user.getPassword() %></p>
        <% } %>
        <a href="Adminsuccess.html" class="go-back">Go Back</a>
    </div>
</body>
</html>
