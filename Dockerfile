# Use official OpenJDK image to build the app
FROM openjdk:17-jdk-slim as build

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/technical-0.0.1-SNAPSHOT.jar /app/technical.jar

# Expose the port the app will run on
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "technical.jar"]
