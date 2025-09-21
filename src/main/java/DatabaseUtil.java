import java.sql.*;

public class DatabaseUtil {
    // Use environment variables for Railway deployment
    static final String URL = System.getenv("DATABASE_URL") != null ? 
        System.getenv("DATABASE_URL") : 
        "jdbc:postgresql://localhost:5433/UserLoginSystem";
    static final String USER = System.getenv("DB_USER") != null ? 
        System.getenv("DB_USER") : 
        "postgres";
    static final String PASS = System.getenv("DB_PASSWORD") != null ? 
        System.getenv("DB_PASSWORD") : 
        "Revanth2005";
    
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