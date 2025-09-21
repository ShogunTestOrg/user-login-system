#!/bin/bash

# Set the port from environment variable or default to 8080
export PORT=${PORT:-8080}

# Configure Tomcat to use the dynamic port
sed -i "s/port=\"8080\"/port=\"${PORT}\"/g" /usr/local/tomcat/conf/server.xml

# Start Tomcat
catalina.sh run