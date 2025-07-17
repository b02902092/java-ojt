# Use Eclipse Temurin JDK 21 as builder
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copy project files
COPY . .

# Build jar (skip tests for faster build)
RUN ./gradlew clean build -x test

# Use a smaller runtime image
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy jar from builder
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
