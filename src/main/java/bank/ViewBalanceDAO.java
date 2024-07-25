package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewBalanceDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/db3";
    private static final String USER = "root";
    private static final String PASSWORD = "Iswarya@1910";

    static {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public double getCurrentBalance(String accountNumber) {
        double balance = 0.0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Obtain database connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Retrieve current balance for the given account number
            String sql = "SELECT balance FROM customer WHERE account_number = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, accountNumber);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                balance = resultSet.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            close(connection, preparedStatement, resultSet);
        }
        return balance;
    }

    private void close(Connection connection, java.sql.Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
