package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResetDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db3";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Iswarya@1910";

    // Method to get database connection
    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found", e);
        }
    }

    // Method to validate the current password
    public boolean validateCurrentPassword(String accountNumber, String currentPassword) throws SQLException {
        String query = "SELECT * FROM customer WHERE account_number = ? AND password = ?";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, accountNumber);
            stmt.setString(2, currentPassword);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    // Method to update the password
    public boolean updatePassword(String accountNumber, String newPassword) throws SQLException {
        String query = "UPDATE customer SET password = ? WHERE account_number = ?";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newPassword);
            stmt.setString(2, accountNumber);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }
}
