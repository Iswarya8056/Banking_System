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

@WebServlet("/DeleteAccountServlet")
public class DeleteAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String jdbcURL = "jdbc:mysql://localhost:3306/db3";
    private static final String jdbcUsername = "root";
    private static final String jdbcPassword = "Iswarya@1910";

    private static final String CHECK_BALANCE = "SELECT balance FROM customer WHERE account_number=? AND mobile_no=?";
    private static final String DELETE_ACCOUNT = "DELETE FROM customer WHERE account_number=? AND mobile_no=?";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        String mobileNo = request.getParameter("mobileNo");

        Connection connection = null;
        PreparedStatement checkBalanceStmt = null;
        PreparedStatement deleteAccountStmt = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            // Check if the balance is zero
            checkBalanceStmt = connection.prepareStatement(CHECK_BALANCE);
            checkBalanceStmt.setString(1, accountNumber);
            checkBalanceStmt.setString(2, mobileNo);
            resultSet = checkBalanceStmt.executeQuery();

            if (resultSet.next()) {
                double balance = resultSet.getDouble("balance");
                if (balance == 0) {
                    // Delete the account
                    deleteAccountStmt = connection.prepareStatement(DELETE_ACCOUNT);
                    deleteAccountStmt.setString(1, accountNumber);
                    deleteAccountStmt.setString(2, mobileNo);
                    int rowsDeleted = deleteAccountStmt.executeUpdate();

                    if (rowsDeleted > 0) {
                        request.setAttribute("successMessage", "Account deleted successfully!");
                    } else {
                        request.setAttribute("errorMessage", "Failed to delete account. Please check the account number and mobile number.");
                    }
                } else {
                    request.setAttribute("errorMessage", "Cannot delete account with non-zero balance.");
                }
            } else {
                request.setAttribute("errorMessage", "Account not found. Please check the account number and mobile number.");
            }
            request.getRequestDispatcher("DeleteAccountResult.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            request.setAttribute("errorMessage", "Exception occurred: " + ex.getMessage());
            request.getRequestDispatcher("DeleteAccountResult.jsp").forward(request, response);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (checkBalanceStmt != null) checkBalanceStmt.close();
                if (deleteAccountStmt != null) deleteAccountStmt.close();
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
