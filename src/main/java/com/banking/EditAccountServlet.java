package com.banking;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditAccountServlet")
public class EditAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection details
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/db3";
    private static final String jdbcUsername = "root";
    private static final String jdbcPassword = "Iswarya@1910";

    // SQL query to fetch account details
    private static final String FETCH_ACCOUNT_DETAILS = "SELECT full_name, address, mobile_no, email, dob, accountType FROM customer WHERE account_number=?";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.jdbc.Driver");
            // Establish database connection
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            // Prepare SQL statement
            preparedStatement = connection.prepareStatement(FETCH_ACCOUNT_DETAILS);
            preparedStatement.setString(1, accountNumber);
            resultSet = preparedStatement.executeQuery();

            // Check if account exists and fetch details
            if (resultSet.next()) {
                request.setAttribute("accountNumber", accountNumber);
                request.setAttribute("fullName", resultSet.getString("full_name"));
                request.setAttribute("address", resultSet.getString("address"));
                request.setAttribute("mobileNo", resultSet.getString("mobile_no"));
                request.setAttribute("email", resultSet.getString("email"));
                request.setAttribute("dob", resultSet.getString("dob"));
                request.setAttribute("accountType", resultSet.getString("accountType"));

                // Forward to JSP page for editing
                request.getRequestDispatcher("EditAccountDetails.jsp").forward(request, response);
            } else {
                // Account not found
                request.setAttribute("errorMessage", "Account not found.");
                request.getRequestDispatcher("EditAccount.jsp").forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            request.setAttribute("errorMessage", "Exception occurred: " + ex.getMessage());
            request.getRequestDispatcher("EditAccount.jsp").forward(request, response);
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
