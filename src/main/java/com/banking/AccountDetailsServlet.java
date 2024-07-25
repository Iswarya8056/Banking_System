package com.banking;

import java.io.IOException;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpSession;

@WebServlet("/AccountDetailsServlet")
public class AccountDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String jdbcURL = "jdbc:mysql://localhost:3306/db3";
    private static final String jdbcUsername = "root";
    private static final String jdbcPassword = "Iswarya@1910";

    private static final String SELECT_ACCOUNT_BY_NUMBER = "SELECT full_name, address, mobile_no, email, dob, accountType FROM customer WHERE account_number = ?";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String accountNumber = (String) session.getAttribute("account_number");

        if (accountNumber == null) {
            response.sendRedirect("CustomerSuccess.html");
            return;
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            preparedStatement = connection.prepareStatement(SELECT_ACCOUNT_BY_NUMBER);
            preparedStatement.setString(1, accountNumber);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String fullName = resultSet.getString("full_name");
                String address = resultSet.getString("address");
                String mobileNo = resultSet.getString("mobile_no");
                String email = resultSet.getString("email");
                String dob = resultSet.getString("dob");
                String accountType = resultSet.getString("accountType");

                out.println("<html><head><title>Account Details</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; background-color: #e0f7fa; margin: 0; padding: 20px; }");
                out.println(".container { max-width: 600px; margin: 0 auto; padding: 15px; background-color: #ffffff; border-radius: 8px; box-shadow: 0 0 8px rgba(0, 0, 0, 0.1); }");
                out.println("h2 { color: #007bff; }");
                out.println("p { font-size: 14px; color: #333; }");
                out.println("</style>");
                out.println("</head><body>");
                out.println("<div class='container'>");
                out.println("<h2>Account Details</h2>");
                out.println("<p><strong>Full Name:</strong> " + fullName + "</p>");
                out.println("<p><strong>Address:</strong> " + address + "</p>");
                out.println("<p><strong>Mobile Number:</strong> " + mobileNo + "</p>");
                out.println("<p><strong>Email:</strong> " + email + "</p>");
                out.println("<p><strong>Date of Birth:</strong> " + dob + "</p>");
                out.println("<p><strong>Account Type:</strong> " + accountType + "</p>");
                out.println("</div>");
                out.println("</body></html>");
            } else {
                out.println("<html><head><title>Account Details</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; background-color: #e0f7fa; margin: 0; padding: 20px; }");
                out.println(".container { max-width: 600px; margin: 0 auto; padding: 15px; background-color: #ffffff; border-radius: 8px; box-shadow: 0 0 8px rgba(0, 0, 0, 0.1); }");
                out.println("h2 { color: #007bff; }");
                out.println("p { font-size: 14px; color: #333; }");
                out.println("</style>");
                out.println("</head><body>");
                out.println("<div class='container'>");
                out.println("<h2>Account Not Found</h2>");
                out.println("<p>No account details found for account number: " + accountNumber + "</p>");
                out.println("</div>");
                out.println("</body></html>");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            out.println("<html><head><title>Error</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #e0f7fa; margin: 0; padding: 20px; }");
            out.println(".container { max-width: 600px; margin: 0 auto; padding: 15px; background-color: #ffffff; border-radius: 8px; box-shadow: 0 0 8px rgba(0, 0, 0, 0.1); }");
            out.println("h2 { color: #007bff; }");
            out.println("p { font-size: 14px; color: #333; }");
            out.println("</style>");
            out.println("</head><body>");
            out.println("<div class='container'>");
            out.println("<h2>Exception occurred</h2>");
            out.println("<p>" + ex.getMessage() + "</p>");
            out.println("</div>");
            out.println("</body></html>");
        } finally {
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
