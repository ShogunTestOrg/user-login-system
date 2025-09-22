import java.sql.*;

public class DatabaseUtil {
    // Database connection configuration
    static final String URL = buildDatabaseUrl();
    static final String USER = "postgres";
    static final String PASS = "Revanth2005";
    
    private static String buildDatabaseUrl() {
        // Check if remote database host is specified
        String dbHost = System.getenv("DB_HOST");
        
        if (dbHost != null) {
            // Remote database connection - use standard PostgreSQL port and default database name
            return String.format("jdbc:postgresql://%s:5433/UserLoginSystem", dbHost);
        } else {
            // Local database connection
            return "jdbc:postgresql://localhost:5433/UserLoginSystem";
        }
    }
    
    static {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("PostgreSQL driver loaded successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL driver not found: " + e.getMessage());
        }
    }
    
    public static Connection getConnection() throws SQLException {
        System.out.println("Attempting database connection with:");
        System.out.println("URL: " + URL);
        System.out.println("USER: " + USER);
        
        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        System.out.println("Database connection successful!");
        return conn;
    }
}