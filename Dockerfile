FROM openjdk:17

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

# Copy the JAR file
COPY build/libs/*.jar app.jar

# Volume for temporary storage
VOLUME /tmp

# Expose port 5000
EXPOSE 5000

# Entry point
ENTRYPOINT ["java", "-jar", "app.jar"]
