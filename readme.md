# User Login System

A Java web application built with Servlets and PostgreSQL for user authentication and session management. This system provides user registration, login, logout, and profile management functionality.

## 🚀 Features

- **User Registration**: Create new user accounts with validation
- **User Login**: Secure authentication with session management
- **Profile Management**: View and manage user profile information
- **Session Handling**: Automatic logout and session security
- **PostgreSQL Database**: Reliable data persistence
- **Responsive Web Interface**: Clean HTML forms for user interaction

## 📋 Prerequisites

Before setting up the project locally, ensure you have the following installed:

- **Java Development Kit (JDK) 17 or higher**
- **Apache Maven 3.6+**
- **PostgreSQL 12 or higher**
- **Apache Tomcat 9.0+ (optional - Maven can run embedded server)**
- **Git** (for cloning the repository)

## 🛠️ Local Development Setup

### 1. Clone the Repository

```bash
git clone https://github.com/ShogunTestOrg/user-login-system.git
cd user-login-system
```

### 2. Database Setup

#### Install and Configure PostgreSQL

1. **Install PostgreSQL** from [https://www.postgresql.org/download/](https://www.postgresql.org/download/)

2. **Start PostgreSQL Service**
   - Windows: Start via Services or pgAdmin
   - macOS: `brew services start postgresql`
   - Linux: `sudo systemctl start postgresql`

3. **Create Database and Tables**

Execute the provided SQL setup script using one of these methods:

**Method 1: Using psql command line (Recommended)**
```bash
# Navigate to project directory
cd path/to/UserLoginSystem

# Execute the SQL file (Windows)
psql -U postgres -f database_setup.sql

# Execute the SQL file (macOS/Linux)
psql -U postgres -f database_setup.sql
```

**Method 2: Using psql interactive mode**
```bash
# Connect to PostgreSQL as postgres user
psql -U postgres

# Execute the SQL file from within psql
\i database_setup.sql

# Exit psql
\q
```

**Method 3: Using pgAdmin**
1. Open pgAdmin and connect to your PostgreSQL server
2. Right-click on "Databases" → "Create" → "Database"
3. Or open the Query Tool and copy-paste the contents of `database_setup.sql`
4. Execute the script

#### Configure Database Connection

The application is configured to connect to:
- **Host**: `localhost`
- **Port**: `5433` (local) / `5432` (remote)
- **Database**: `UserLoginSystem`
- **Username**: `postgres`
- **Password**: `YourDBPassword`

If your PostgreSQL setup is different, you can either:
1. Update the credentials in `src/main/java/DatabaseUtil.java`
2. Or change your PostgreSQL configuration to match the default settings

### 3. Build and Run the Application

#### Option A: Using Maven with Embedded Tomcat (Recommended)

```bash
# Clean and compile the project
mvn clean compile

# Run with embedded Tomcat server
mvn tomcat7:run
```

The application will be available at: **http://localhost:8080**

#### Option B: Using Maven with Jetty

```bash
# Run with embedded Jetty server
mvn jetty:run
```

#### Option C: Deploy to External Tomcat

```bash
# Build WAR file
mvn clean package

# Deploy the generated WAR file
# Copy target/user-login.war to your Tomcat's webapps directory
# Start Tomcat server
```

### 4. Access the Application

Once the server is running, open your web browser and navigate to:

- **Home Page**: http://localhost:8080/
- **Login Page**: http://localhost:8080/login.html
- **Register Page**: http://localhost:8080/register.html

## 🌐 Application Endpoints

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/` | GET | Home page |
| `/login.html` | GET | Login form |
| `/register.html` | GET | Registration form |
| `/login` | POST | Process login |
| `/register` | POST | Process registration |
| `/profile` | GET | User profile (requires login) |
| `/logout` | GET/POST | Logout user |

## 📁 Project Structure

```
UserLoginSystem/
├── src/
│   └── main/
│       ├── java/
│       │   ├── DatabaseUtil.java      # Database connection utility
│       │   ├── LoginServlet.java      # Handles user login
│       │   ├── RegisterServlet.java   # Handles user registration
│       │   ├── ProfileServlet.java    # User profile management
│       │   └── LogoutServlet.java     # Handles user logout
│       └── webapp/
│           ├── index.html             # Home page
│           ├── login.html             # Login form
│           ├── register.html          # Registration form
│           └── WEB-INF/
│               └── web.xml            # Web application configuration
├── target/                            # Compiled classes and WAR file
├── database_setup.sql                # Database setup script
├── pom.xml                           # Maven configuration
├── Dockerfile                        # Docker container configuration
└── README.md                         # This file
```

## 🐋 Docker Deployment (Optional)

For containerized deployment:

```bash
# Build Docker image
docker build -t user-login-system .

# Run container
docker run -p 8080:8080 user-login-system
```

## 🔧 Configuration

### Database Configuration

The database connection is configured in `DatabaseUtil.java`:

- **Local Development**: Uses `localhost:5433`
- **Remote Hosting**: Set `DB_HOST` environment variable

```bash
# For remote database
export DB_HOST=your.remote.database.host
```

### Server Port Configuration

Default port is `8080`. To change:

```bash
# Using Maven
mvn tomcat7:run -Dserver.port=9090

# Using environment variable
export SERVER_PORT=9090
```

## 🚨 Troubleshooting

### Common Issues

1. **Database Connection Failed**
   ```
   Solution: Verify PostgreSQL is running and credentials are correct
   Check: localhost:5433 is accessible
   Verify: Database "UserLoginSystem" exists
   ```

2. **Port Already in Use**
   ```
   Solution: Change port using -Dserver.port=XXXX
   Or: Kill the process using the port
   ```

3. **Maven Build Fails**
   ```
   Solution: Ensure JDK 17+ is installed and JAVA_HOME is set
   Run: mvn clean install -U
   ```

4. **PostgreSQL Authentication Failed**
   ```
   Solution: Check pg_hba.conf file
   Ensure: Password authentication is enabled for localhost
   ```

### Database Troubleshooting

```sql
-- Check if database exists
SELECT datname FROM pg_database WHERE datname = 'UserLoginSystem';

-- Check if users table exists
\dt

-- View table structure
\d users

-- Check if there are any users
SELECT * FROM users;
```

## 🔒 Security Notes

- Passwords should be hashed in production (consider bcrypt)
- Use HTTPS in production environments
- Implement proper session timeout
- Add CSRF protection for forms
- Validate and sanitize all user inputs

## 📝 Development Notes

- Built with Java 17 and Maven
- Uses PostgreSQL JDBC driver version 42.6.0
- Servlet API 4.0.1
- No external frameworks (pure Servlets)
- Session-based authentication

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## 📄 License

This project is for educational purposes.

---

**Happy Coding! 🎉**

For any issues or questions, please refer to the troubleshooting section or create an issue in the repository.
