# Use an official OpenJDK image to build the Kotlin project
FROM openjdk:23-jdk-slim AS builder

# Set environment variables for Maven
ENV MAVEN_VERSION=3.8.4
ENV MAVEN_HOME=/opt/maven
ENV PATH=$MAVEN_HOME/bin:$PATH

# Install Maven
RUN apt-get update && \
    apt-get install -y curl git && \
    curl -fsSL "https://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz" | tar -xz -C /opt && \
    ln -s /opt/apache-maven-$MAVEN_VERSION /opt/maven

# Create the build directory
RUN mkdir -p /build

# Set the working directory
WORKDIR /build

# Copy the project files into the container
COPY . .

# Build the project using Maven
RUN mvn clean package -DskipTests

# Final stage for running the application
FROM openjdk:23-jdk-slim

# Create the app directory
RUN mkdir -p /spotify

# Set the working directory
WORKDIR /spotify

# Copy the built artifacts (jar file) from the builder stage
COPY --from=builder /build/target/*.jar spotify.jar

# Expose the port your app runs on (adjust this as necessary)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "spotify.jar"]