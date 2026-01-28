# ----------- Build stage -----------
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

# Copy all project files
COPY . .

# Build the Spring Boot JAR (skip tests)
RUN ./mvnw clean package -DskipTests

# ----------- Runtime stage --------
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy only the built JAR from the build stage
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
