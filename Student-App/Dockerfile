# Build stage
FROM maven:3.9.9-eclipse-temurin-22 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:22-jdk

WORKDIR /app

COPY --from=build /app/target/*.jar student-app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/student-app.jar"]