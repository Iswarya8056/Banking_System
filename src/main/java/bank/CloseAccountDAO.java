package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CloseAccountDAO {

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

    public boolean closeAccount(String accountNumber) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean success = false;

        try {
            // Obtain database connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Check if the account balance is zero
            String checkBalanceSql = "SELECT balance FROM customer WHERE account_number = ?";
            preparedStatement = connection.prepareStatement(checkBalanceSql);
            preparedStatement.setString(1, accountNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                double balance = resultSet.getDouble("balance");
                if (balance != 0) {
                    return false;
                }
            }

            // Delete the account if balance is zero
            String sql = "DELETE FROM customer WHERE account_number = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, accountNumber);
            int rowsAffected = preparedStatement.executeUpdate();

            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            close(connection, preparedStatement);
        }
        return success;
    }

    private void close(Connection connection, java.sql.Statement statement) {
        try {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
