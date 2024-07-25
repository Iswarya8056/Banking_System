package bank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DepositDAO {

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

    public boolean depositAmount(String accountNumber, double amount) {
        boolean isDeposited = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Obtain database connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Update account balance
            String updateAccountSQL = "UPDATE customer SET balance = balance + ? WHERE account_number = ?";
            preparedStatement = connection.prepareStatement(updateAccountSQL);
            preparedStatement.setDouble(1, amount);
            preparedStatement.setString(2, accountNumber);
            
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                // Insert transaction record
                String insertTransactionSQL = "INSERT INTO transaction (account_number, transaction_amount, transaction_type) VALUES (?, ?, 'DEPOSIT')";
                preparedStatement = connection.prepareStatement(insertTransactionSQL);
                preparedStatement.setString(1, accountNumber);
                preparedStatement.setDouble(2, amount);

                preparedStatement.executeUpdate();
                isDeposited = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            close(connection, preparedStatement, null);
        }
        return isDeposited;
    }

    private void close(Connection connection, java.sql.Statement statement, java.sql.ResultSet resultSet) {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
