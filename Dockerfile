# Use official OpenJDK 21 image
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Install Maven
RUN apt-get update && apt-get install -y maven && apt-get clean

# Copy project files
COPY . .

# Give execute permission to mvnw if present
RUN chmod +x mvnw || true

# Build the project (skip tests to speed up)
RUN ./mvnw package -DskipTests || mvn package -DskipTests

# Expose app port
EXPOSE 8080

# Run the JAR file (update name if different)
CMD ["java", "-jar", "target/backend-0.0.1-SNAPSHOT.jar"]
