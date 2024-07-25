<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Account Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 50%;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 8px rgba(0,0,0,0.1);
            border-radius: 8px;
        }
        h2 {
            text-align: center;
            color: #4CAF50; /* Green color */
        }
        form {
            display: flex;
            flex-direction: column;
            padding: 0 20px;
        }
        label {
            font-size: 14px;
            color: #333;
            margin-bottom: 5px;
        }
        input[type="text"],
        input[type="email"],
        input[type="date"] {
            width: calc(100% - 16px); /* Adjust width for padding */
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
        }
        button {
            padding: 8px 16px;
            background-color: #4CAF50; /* Green color */
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
            align-self: center;
        }
        button:hover {
            background-color: #45a049; /* Darker green on hover */
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Edit Account Details</h2>
        <form action="UpdateAccountServlet" method="post">
            <input type="hidden" name="accountNumber" value="${accountNumber}">
            <label for="fullName">Full Name:</label>
            <input type="text" id="fullName" name="fullName" value="${fullName}" required>
            
            <label for="address">Address:</label>
            <input type="text" id="address" name="address" value="${address}" required>
            
            <label for="mobileNo">Mobile Number:</label>
            <input type="text" id="mobileNo" name="mobileNo" value="${mobileNo}" required>
            
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="${email}" required>
            
            <label for="dob">Date of Birth:</label>
            <input type="date" id="dob" name="dob" value="${dob}" required>
            
            <label for="accountType">Account Type:</label>
            <input type="text" id="accountType" name="accountType" value="${accountType}" required>
            
            <button type="submit">Update</button>
        </form>
    </div>
</body>
</html>
