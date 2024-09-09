# Use a base image that contains Java runtime
FROM openjdk:17-jdk-alpine

# Copy the JAR file to the container
COPY target/ems-0.0.1-SNAPSHOT.jar /app.jar

# Expose the port your Spring Boot application runs on (default is 8080)
EXPOSE 8080

# Set the entry point to run your JAR file
ENTRYPOINT ["java", "-jar", "/app.jar"]
