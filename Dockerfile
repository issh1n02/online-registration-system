FROM eclipse-temurin:11-jre-alpine

LABEL maintainer="student"
LABEL description="Online Registration System - Experiment 8"

WORKDIR /app

# Copy the JAR file
COPY target/online-registration-system.jar app.jar

# Expose port
EXPOSE 8080

# Install wget for health check
RUN apk add --no-cache wget

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD wget --no-verbose --tries=1 --spider http://localhost:8080/ || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
