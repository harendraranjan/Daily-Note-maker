# Build stage
FROM openjdk:17-jdk-slim AS build

WORKDIR /app

# Copy necessary files for dependency resolution and build
COPY pom.xml mvnw ./
COPY .mvn .mvn

# Resolve dependencies and package the application
RUN ./mvnw dependency:resolve
COPY src src
RUN ./mvnw package -DskipTests

# Production stage
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the packaged JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
