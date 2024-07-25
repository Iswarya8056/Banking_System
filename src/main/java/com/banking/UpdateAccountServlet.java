package com.banking;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateAccountServlet")
public class UpdateAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String jdbcURL = "jdbc:mysql://localhost:3306/db3";
    private static final String jdbcUsername = "root";
    private static final String jdbcPassword = "Iswarya@1910";

    private static final String UPDATE_ACCOUNT_DETAILS = "UPDATE customer SET full_name=?, address=?, mobile_no=?, email=?, dob=?, accountType=? WHERE account_number=?";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobileNo");
        String email = request.getParameter("email");
        String dob = request.getParameter("dob");
        String accountType = request.getParameter("accountType");

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT_DETAILS);
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, mobileNo);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, dob);
            preparedStatement.setString(6, accountType);
            preparedStatement.setString(7, accountNumber);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                request.setAttribute("successMessage", "Account details updated successfully!");
            } else {
                request.setAttribute("errorMessage", "Failed to update account details.");
            }
            request.getRequestDispatcher("EditAccountDetails.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            request.setAttribute("errorMessage", "Exception occurred: " + ex.getMessage());
            request.getRequestDispatcher("EditAccountDetails.jsp").forward(request, response);
        } finally {
            try {
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
