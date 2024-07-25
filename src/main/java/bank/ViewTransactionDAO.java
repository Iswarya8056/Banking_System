package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bankmodel.Transaction;

public class ViewTransactionDAO {

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

    public List<Transaction> getTransactions(String accountNumber) {
        List<Transaction> transactions = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Obtain database connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Retrieve transactions for the given account number
            String sql = "SELECT * FROM transaction WHERE account_number = ? ORDER BY transaction_date DESC";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, accountNumber);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int transactionId = resultSet.getInt("transaction_id");
                double amount = resultSet.getDouble("transaction_amount");
                String type = resultSet.getString("transaction_type");
                java.sql.Timestamp date = resultSet.getTimestamp("transaction_date");

                Transaction transaction = new Transaction(transactionId, accountNumber, amount, type, date);
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            close(connection, preparedStatement, resultSet);
        }
        return transactions;
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
