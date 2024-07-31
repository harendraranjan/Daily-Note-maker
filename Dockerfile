FROM maven:3.8.1-openjdk-17-slim as build
COPY . /app
WORKDIR /app
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /app/target/your-application.jar /app/your-application.jar
CMD ["java", "-jar", "/app/your-application.jar"]
