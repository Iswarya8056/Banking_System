package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bankmodel.user;

public class CreateAccountDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/db3";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Iswarya@1910";

    private static final String INSERT_USERS_SQL = "INSERT INTO customer (full_name, address, mobile_no, email, dob, balance, password, accountType, account_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public void addUser(user user) throws SQLException {
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {

            // Set parameters
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getAddress());
            preparedStatement.setString(3, user.getMobile());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getDob());
            preparedStatement.setDouble(6, user.getBalance());
            preparedStatement.setString(7, user.getPassword());
            preparedStatement.setString(8, user.getAccountType());
            preparedStatement.setString(9, user.getAccountNumber());

            // Log SQL query
            System.out.println("Executing query: " + preparedStatement.toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
            throw e; // Re-throwing the exception for handling in the servlet
        }
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
