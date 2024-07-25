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

@WebServlet("/ViewAccountServlet")
public class ViewAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String jdbcURL = "jdbc:mysql://localhost:3306/db3";
    private static final String jdbcUsername = "root";
    private static final String jdbcPassword = "Iswarya@1910";

    private static final String SELECT_ACCOUNT_BY_NUMBER = "SELECT full_name, address, mobile_no, email, dob, accountType FROM customer WHERE account_number = ?";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String accountNumber = request.getParameter("accountNumber");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            preparedStatement = connection.prepareStatement(SELECT_ACCOUNT_BY_NUMBER);
            preparedStatement.setString(1, accountNumber);

            resultSet = preparedStatement.executeQuery();

            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>Account Details</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }");
            out.println(".container { width: 50%; margin: 50px auto; padding: 20px; background-color: #fff; box-shadow: 0 0 10px rgba(0,0,0,0.1); border-radius: 8px; }");
            out.println("h2 { text-align: center; color: #4CAF50; }"); // Green color
            out.println("p { font-size: 16px; color: #333; }");
            out.println("strong { color: #4CAF50; }"); // Green color
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"container\">");

            if (resultSet.next()) {
                String fullName = resultSet.getString("full_name");
                String address = resultSet.getString("address");
                String mobileNo = resultSet.getString("mobile_no");
                String email = resultSet.getString("email");
                String dob = resultSet.getString("dob");
                String accountType = resultSet.getString("accountType");

                out.println("<h2>Account Details</h2>");
                out.println("<p><strong>Full Name:</strong> " + fullName + "</p>");
                out.println("<p><strong>Address:</strong> " + address + "</p>");
                out.println("<p><strong>Mobile Number:</strong> " + mobileNo + "</p>");
                out.println("<p><strong>Email:</strong> " + email + "</p>");
                out.println("<p><strong>Date of Birth:</strong> " + dob + "</p>");
                out.println("<p><strong>Account Type:</strong> " + accountType + "</p>");
            } else {
                out.println("<h2>Account Not Found</h2>");
                out.println("<p>No account details found for account number: " + accountNumber + "</p>");
            }

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            out.println("<html><head><title>Error</title></head><body>");
            out.println("<h2>Exception occurred</h2>");
            out.println("<p>" + ex.getMessage() + "</p>");
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
