# Use official lightweight OpenJDK 21 image
FROM openjdk:21-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy everything into the container
COPY . .

# If using Maven to build your project
RUN ./mvnw package -DskipTests || mvn package -DskipTests

# Expose your app's port (Render expects this)
EXPOSE 8080

# Run your application (update jar name if needed)
CMD ["java", "-jar", "target/backend-0.0.1-SNAPSHOT.jar"]
