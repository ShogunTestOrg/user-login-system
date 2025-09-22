-- User Login System Database Setup
-- This script creates the database and required tables for the User Login System

-- Connect as postgres superuser first, then run this script

-- Create database
CREATE DATABASE "UserLoginSystem";

-- Connect to the database
\c "UserLoginSystem";

-- Create users table
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Verify table creation
\dt

-- Display table structure
\d users;

-- Optional: Insert a test user (password should be hashed in production)
-- INSERT INTO users (username, email, password) 
-- VALUES ('testuser', 'test@example.com', 'testpassword123');

-- Display success message
SELECT 'Database setup completed successfully!' as message;