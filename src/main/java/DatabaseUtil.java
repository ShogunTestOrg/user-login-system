import java.sql.*;

public class DatabaseUtil {
    static final String URL = "jdbc:postgresql://localhost:5433/UserLoginSystem";
    static final String USER = "postgres";
    static final String PASS = "Revanth2005";
    
    static {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("PostgreSQL driver loaded successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL driver not found: " + e.getMessage());
        }
    }
    
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        System.out.println("Database connection successful!");
        return conn;
    }
}