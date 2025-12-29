# Stage 1: Build the application (if using a multi-stage build)
#FROM maven:3.9.6-openjdk-24 AS build
#WORKDIR /app
#COPY pom.xml .
#COPY src ./src
#RUN mvn clean package -DskipTests

# Stage 2: Create the final image
FROM openjdk:24-jdk-slim
#WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8005
CMD ["java", "-jar", "app.jar"]
