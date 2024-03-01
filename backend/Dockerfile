FROM maven:3.8.3-openjdk-17 AS builder
WORKDIR /app
COPY ./pom.xml .
COPY ./src ./src
RUN mvn clean package -DskipTests

# Этап сборки контейнера
FROM openjdk:17-jdk-slim
EXPOSE 80
WORKDIR /app
COPY --from=builder /app/target/backend-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]
