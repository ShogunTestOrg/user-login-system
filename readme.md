# User Login System

A Java Servlet web application that provides user login and registration functionality with PostgreSQL database integration, deployable on Railway.

## Features

- User registration with data stored in PostgreSQL
- User login authentication
- Secure password storage using PostgreSQL's SCRAM-SHA-256 authentication
- Simple and clean front-end pages for login and registration
- **Railway deployment ready with Docker containerization**

## Local Development

### Prerequisites
- Java 17
- Maven 3.9+
- PostgreSQL database

### Setup
1. Clone the repository
2. Set up PostgreSQL database
3. Update database connection in `DatabaseUtil.java` if needed
4. Run the application:
   ```bash
   mvn clean compile
   mvn tomcat7:run
   ```
5. Access at `http://localhost:8080`

## Railway Deployment

### Prerequisites
- Railway account
- GitHub repository

### Step 1: Prepare Your Repository
1. Push your code to GitHub
2. Make sure all the deployment files are included:
   - `Dockerfile`
   - `railway.json`
   - Updated `pom.xml`

### Step 2: Set Up PostgreSQL Database on Railway
1. Go to [Railway](https://railway.app)
2. Create a new project
3. Add a PostgreSQL database service:
   - Click "Add Service"
   - Select "Database" → "PostgreSQL"
   - Railway will automatically create the database and provide connection details

### Step 3: Deploy Your Application
1. In your Railway project, click "Add Service"
2. Select "GitHub Repo"
3. Connect your repository
4. Railway will automatically detect the `Dockerfile` and build your application

### Step 4: Configure Environment Variables
In your Railway web service, add these environment variables:

- `DATABASE_URL`: Use the PostgreSQL connection string from Railway (format: `jdbc:postgresql://[host]:[port]/[database]`)
- `DB_USER`: PostgreSQL username from Railway
- `DB_PASSWORD`: PostgreSQL password from Railway

**Note**: Railway provides these values automatically in the PostgreSQL service. You can find them in:
- Database service → Variables tab
- Copy the connection details and adapt them for your application

### Step 5: Create Database Tables
After deployment, you'll need to create the necessary tables in your PostgreSQL database. Connect to your Railway PostgreSQL instance and run your table creation scripts.

### Example Environment Variables Setup
```
DATABASE_URL=jdbc:postgresql://containers-us-west-x.railway.app:xxxx/railway
DB_USER=postgres  
DB_PASSWORD=your-railway-db-password
```

### Railway Configuration Files

#### `Dockerfile`
- Multi-stage build using Maven and Tomcat
- Builds the WAR file and deploys to Tomcat
- Exposes port 8080

#### `railway.json`
- Specifies Dockerfile build
- Configures restart policy
- Sets start command

## Troubleshooting

### Common Issues
1. **Database Connection**: Ensure environment variables are correctly set
2. **Port Issues**: Railway automatically assigns ports, the application listens on 8080
3. **Build Failures**: Check Java version compatibility (using Java 17)

### Logs
Check Railway logs in the deployment dashboard for debugging information.

## Project Structure

