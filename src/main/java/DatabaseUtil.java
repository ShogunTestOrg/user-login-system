import java.sql.*;

public class DatabaseUtil {
    // Use environment variables for Railway deployment
    static final String URL = System.getenv("PGDATABASE") != null ? 
        System.getenv("PGDATABASE") : 
        "jdbc:postgresql://localhost:5433/UserLoginSystem";
    static final String USER = System.getenv("PGUSER") != null ? 
        System.getenv("PGUSER") : 
        "postgres";
    static final String PASS = System.getenv("PGPASSWORD") != null ? 
        System.getenv("PGPASSWORD") : 
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