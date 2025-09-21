import java.sql.*;

public class DatabaseUtil {
    // Use environment variables for Railway deployment
    static final String URL = buildDatabaseUrl();
    static final String USER = System.getenv("PGUSER") != null ? 
        System.getenv("PGUSER") : 
        "postgres";
    static final String PASS = System.getenv("PGPASSWORD") != null ? 
        System.getenv("PGPASSWORD") : 
        "Revanth2005";
    
    private static String buildDatabaseUrl() {
        // First try to use DATABASE_URL (Railway's standard variable)
        String databaseUrl = System.getenv("DATABASE_URL");
        if (databaseUrl != null) {
            // Railway provides DATABASE_URL in postgres:// format, convert to jdbc:postgresql://
            if (databaseUrl.startsWith("postgres://")) {
                databaseUrl = databaseUrl.replace("postgres://", "jdbc:postgresql://");
            }
            return databaseUrl;
        }
        
        // Fallback: Check if Railway environment variables are available
        String pgHost = System.getenv("PGHOST");
        String pgPort = System.getenv("PGPORT");
        String pgDatabase = System.getenv("PGDATABASE");
        
        if (pgHost != null && pgPort != null && pgDatabase != null) {
            // Railway deployment - construct JDBC URL from Railway variables
            return String.format("jdbc:postgresql://%s:%s/%s", pgHost, pgPort, pgDatabase);
        } else {
            // Local development fallback
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
        System.out.println("Environment variables:");
        System.out.println("PGHOST: " + System.getenv("PGHOST"));
        System.out.println("PGPORT: " + System.getenv("PGPORT"));
        System.out.println("PGDATABASE: " + System.getenv("PGDATABASE"));
        System.out.println("PGUSER: " + System.getenv("PGUSER"));
        
        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        System.out.println("Database connection successful!");
        return conn;
    }
}