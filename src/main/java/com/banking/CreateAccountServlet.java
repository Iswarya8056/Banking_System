package com.banking;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.CreateAccountDAO;
import bankmodel.user;

@WebServlet("/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 8;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve form parameters
            String username = request.getParameter("username");
            String address = request.getParameter("address");
            String mobile = request.getParameter("mobile");
            String email = request.getParameter("email");
            String dob = request.getParameter("dob");
            String balanceStr = request.getParameter("balance");
            String accountType = request.getParameter("accounttype");

            System.out.println("Received parameters:");
            System.out.println("Username: " + username);
            System.out.println("Address: " + address);
            System.out.println("Mobile: " + mobile);
            System.out.println("Email: " + email);
            System.out.println("DOB: " + dob);
            System.out.println("Balance: " + balanceStr);
            System.out.println("Account Type: " + accountType);

            // Convert balance to decimal (assuming it's passed as a string)
            double balance = Double.parseDouble(balanceStr);

            // Generate a unique 10-digit account number
            String accountNumber = generateAccountNumber();

            // Generate a temporary password if none is provided
            String password = generateTemporaryPassword();
            System.out.println("Generated temporary password: " + password);

            // Create a new User object
            user user = new user(username, address, mobile, email, dob, balance, accountType, accountNumber, password);

            // Save the user to the database using a DAO (Data Access Object)
            CreateAccountDAO userDao = new CreateAccountDAO();
            userDao.addUser(user);

            // Store user details in session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // Redirect to a success page
            response.sendRedirect("registrationsuccess.jsp");

        } catch (SQLException e) {
            // Handle database insertion error
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("registration-error.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            // Handle parsing error for balance
            e.printStackTrace();
            request.setAttribute("errorMessage", "Invalid balance format: " + e.getMessage());
            request.getRequestDispatcher("registration-error.jsp").forward(request, response);

        } catch (Exception e) {
            // Handle other unexpected errors
            e.printStackTrace();
            request.setAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
            request.getRequestDispatcher("registration-error.jsp").forward(request, response);
        }
    }

    private String generateAccountNumber() {
        Random random = new Random();
        // Generate a random 10-digit number (between 1000000000 and 9999999999)
        long num = (long) (1000000000L + random.nextDouble() * 9000000000L);
        return String.valueOf(num);
    }

    private String generateTemporaryPassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(index));
        }
        return password.toString();
    }
}