# Stage 1: Build the app
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copy project files
COPY . .

RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose port (optional)
EXPOSE 8080

# Run the app using Vercel PORT variable
ENTRYPOINT ["java","-jar","app.jar"]