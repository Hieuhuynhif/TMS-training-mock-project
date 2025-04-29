# ---------- Stage 1: Build ----------
FROM gradle:8.5-jdk21-alpine AS builder
WORKDIR /app
COPY . .
RUN gradle build -x test

# ---------- Stage 2: Run ----------
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/tms-training-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
