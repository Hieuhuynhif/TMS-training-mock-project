# ------------ Stage 1: Build the JAR ------------
FROM gradle:8.5-jdk23-alpine AS builder
WORKDIR /app
COPY . .
RUN gradle build -x test

# ------------ Stage 2: Run the JAR ------------
FROM eclipse-temurin:23-jdk-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/tms-training-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]