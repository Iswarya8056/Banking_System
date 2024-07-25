package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO1 {
    private String jdbcUrl = "jdbc:mysql://localhost:3306/db3";
    private String dbUser = "root";
    private String dbPassword = "Iswarya@1910";

    public LoginDAO1() {
        // Initialize database connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean validate(String accountNumber, String password) {
        String sql = "SELECT * FROM customer WHERE account_number = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, accountNumber);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean getUserRole(String accountNumber, String password) {
        // The implementation of this method remains the same
        return validate(accountNumber, password);
    }
}
