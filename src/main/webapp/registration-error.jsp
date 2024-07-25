<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Error</title>
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
        .error-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 400px;
        }
        .error-container h2 {
            text-align: center;
            margin-bottom: 20px;
            color: red;
        }
        .error-container p {
            margin: 10px 0;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h2>Registration Error</h2>
        <p>An error occurred during the registration process.</p>
        <p><strong>Error Message:</strong> <%= request.getAttribute("errorMessage") %></p>
    </div>
</body>
</html>
