FROM maven:3.8.1-openjdk-17-slim as build
COPY ..
RUN maven clean package -DskipTests
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /app/target/harendraranjan/Letswork-0.0.1-SNAPSHOT.jar Enotes_Spting_boot_project.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "Enotes_Spting_boot_project.jar"]