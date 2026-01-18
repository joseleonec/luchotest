# Multi-stage build for Spring Boot application
# Stage 1: Build the application
FROM eclipse-temurin:21-jdk-alpine AS build

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml first for better layer caching
COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn
COPY pom.xml .

# Make Maven wrapper executable
RUN chmod +x mvnw

# Download dependencies (this will be cached if pom.xml doesn't change)
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src src

# Build the application without tests first
RUN ./mvnw clean compile

# Stage 2: Test stage - Run tests before proceeding
FROM eclipse-temurin:21-jdk-alpine AS test

WORKDIR /app

# Copy everything from build stage
COPY --from=build /app .

# Run tests (this stage will fail if tests fail)
RUN ./mvnw test

# Stage 3: Package stage - Create final JAR after tests pass
FROM eclipse-temurin:21-jdk-alpine AS package

WORKDIR /app

# Copy everything from test stage (tests have passed at this point)
COPY --from=test /app .

# Create the final JAR package (now that tests passed)
RUN ./mvnw package -DskipTests

# Stage 4: Runtime image
FROM eclipse-temurin:21-jre-alpine


# Create app user for security
RUN addgroup -g 1001 -S appgroup && \
    adduser -u 1001 -S appuser -G appgroup

# Set working directory
WORKDIR /app

# Copy the built JAR from package stage
COPY --from=package /app/target/msclientes-*.jar app.jar

# Change ownership to app user
RUN chown appuser:appgroup app.jar

# Switch to app user
USER appuser

# Expose port (Spring Boot default)
EXPOSE 8081

# Set JVM options for containerized environment
ENV JAVA_OPTS="-Xms256m -Xmx512m -Djava.security.egd=file:/dev/./urandom"

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=30s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8081/actuator/health || exit 1

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]