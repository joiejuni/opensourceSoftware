# Use the official OpenJDK image as base image
FROM openjdk:17 as build

# Install Gradle
RUN apt-get update && apt-get install -y gradle

# Set the working directory
WORKDIR /app

# Copy Gradle files
COPY build.gradle .
COPY settings.gradle .

# Copy the source code
COPY src src

# Build the application
RUN gradle build

# Create a new image and copy the JAR file
FROM openjdk:17
COPY --from=build /app/build/libs/*.jar app.jar

# Set the volume and expose the port
VOLUME /tmp
EXPOSE 5000

# Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
