# User Login System - Remote Hosting Configuration

This application is configured for deployment on cloud platforms like Railway, Heroku, or any containerized hosting service.

## Environment Variables

The application uses environment variables for database configuration:

### Railway (Automatic)
Railway automatically provides these variables when you add a PostgreSQL service:
- `DATABASE_URL` - Complete database connection string
- `PGHOST` - Database host
- `PGPORT` - Database port (usually 5432)
- `PGDATABASE` - Database name
- `PGUSER` - Database username
- `PGPASSWORD` - Database password

### Generic Hosting
For other platforms, set these environment variables:
- `DB_HOST` - Your database host
- `DB_PORT` - Database port (default: 5432)
- `DB_NAME` - Database name (default: UserLoginSystem)
- `DB_USER` - Database username
- `DB_PASSWORD` - Database password
- `PORT` - Application port (default: 8080)

## Deployment

### Railway
1. Connect your GitHub repository to Railway
2. Add a PostgreSQL service
3. Deploy automatically with the provided `railway.json` configuration

### Docker
```bash
# Build the image
docker build -t user-login-system .

# Run with environment variables
docker run -p 8080:8080 \
  -e DB_HOST=your-host \
  -e DB_USER=your-user \
  -e DB_PASSWORD=your-password \
  user-login-system
```

### Local Development
```bash
# Run with Maven Tomcat plugin
mvn clean compile tomcat7:run

# Or with Jetty
mvn clean compile jetty:run
```

## Database Setup

Make sure your PostgreSQL database has the required tables. The application expects:
- Users table with appropriate schema
- Proper database permissions for the configured user

## Features Configured for Remote Hosting

- ✅ Environment variable-based database configuration
- ✅ Dynamic port binding for cloud platforms
- ✅ Docker containerization with security best practices
- ✅ Health checks for container orchestration
- ✅ Production-ready web.xml configuration
- ✅ Memory optimization for cloud deployment
- ✅ Session management configuration
- ✅ Error handling and security constraints

## Security Notes

- Database credentials are handled via environment variables
- Session cookies are configured with HttpOnly flag
- Web-INF directory is protected
- Non-root user in Docker container
- Memory limits set for predictable performance