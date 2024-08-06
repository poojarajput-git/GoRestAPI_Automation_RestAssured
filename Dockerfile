# Use OpenJDK 22 base image (slim version)
FROM openjdk:22-jdk-slim

# Set working directory
WORKDIR /app

# Install Maven
RUN apt-get update && \
    apt-get install -y wget tar && \
    wget https://archive.apache.org/dist/maven/maven-3/3.8.5/binaries/apache-maven-3.8.5-bin.tar.gz && \
    tar -xzf apache-maven-3.8.5-bin.tar.gz -C /opt && \
    ln -s /opt/apache-maven-3.8.5/bin/mvn /usr/bin/mvn

# Copy project files
COPY . /app

# Copy the Maven project files into the container
COPY pom.xml .
COPY src ./src

# Build the project
RUN mvn clean install

# Run Maven tests
CMD ["mvn", "test"]


