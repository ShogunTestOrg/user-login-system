# Use OpenJDK 17 and install Maven for building
FROM openjdk:17-jdk-slim AS build

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Use Tomcat for runtime
FROM tomcat:9.0-jdk17

# Remove default webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy built WAR file to Tomcat webapps
COPY --from=build /app/target/user-login.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 8080
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]