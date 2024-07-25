<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reset Password</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #e0f7fa; /* Light blue background */
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .reset-container {
            background-color: #ffffff;
            padding: 20px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
            border-radius: 8px;
            width: 100%;
            max-width: 400px;
        }
        .reset-container h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #007bff; /* Blue color for the heading */
        }
        .reset-container label {
            display: block;
            margin-bottom: 5px;
            color: #333;
        }
        .reset-container input[type="password"],
        .reset-container input[type="text"] {
            width: calc(100% - 22px);
            padding: 12px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
        }
        .reset-container input[type="submit"] {
            width: 100%;
            padding: 12px;
            background-color: #007bff; /* Blue background for the button */
            border: none;
            border-radius: 4px;
            color: #fff;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .reset-container input[type="submit"]:hover {
            background-color: #0056b3; /* Darker blue on hover */
        }
        .error-message {
            color: #ff0000; /* Red color for error messages */
            margin-bottom: 15px;
            font-size: 14px;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="reset-container">
        <h2>Reset Password</h2>
        <form action="ResetPasswordServlet" method="post">
           
                <div class="error-message">
                
                </div>
          
            
            <label for="currentPassword">Current Password:</label>
            <input type="password" id="currentPassword" name="currentPassword" required>
            
            <label for="newPassword">New Password:</label>
            <input type="password" id="newPassword" name="newPassword" required>
            
            <label for="confirmPassword">Confirm New Password:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required>
            
            <input type="submit" value="Reset Password">
        </form>
    </div>
</body>
</html>
