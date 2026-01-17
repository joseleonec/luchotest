# Docker Setup for Luchotest Application

This document provides instructions for running the Luchotest Spring Boot application using Docker.

## Prerequisites

- Docker Desktop installed and running
- Docker Compose (included with Docker Desktop)

## Quick Start

### 1. Build and Run with Docker Compose

The easiest way to run the application is using Docker Compose, which will start both the Spring Boot application and PostgreSQL database:

```bash
# Build and start all services
docker-compose up --build

# Run in detached mode (background)
docker-compose up --build -d

# View logs
docker-compose logs -f app
```

### 2. Access the Application

- Application: http://localhost:8080
- Health Check: http://localhost:8080/actuator/health
- Database: localhost:5432

### 3. Default Credentials

- **Application Login:**
  - Username: `admin`
  - Password: `admin123`

- **Database:**
  - Host: `localhost:5432`
  - Database: `luchotestdb`
  - Username: `luchotest`
  - Password: `password`

## Advanced Usage

### Build Docker Image Only

```bash
# Build the Docker image
docker build -t luchotest-app .

# Run the container (requires external PostgreSQL)
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://your-db-host:5432/luchotestdb \
  -e SPRING_DATASOURCE_USERNAME=your-username \
  -e SPRING_DATASOURCE_PASSWORD=your-password \
  -e SPRING_SECURITY_USER_NAME=admin \
  -e SPRING_SECURITY_USER_PASSWORD=admin123 \
  luchotest-app
```

### Environment Variables

You can customize the application using these environment variables:

| Variable | Default Value | Description |
|----------|---------------|-------------|
| `SPRING_DATASOURCE_URL` | `jdbc:postgresql://postgres:5432/luchotestdb` | Database connection URL |
| `SPRING_DATASOURCE_USERNAME` | `luchotest` | Database username |
| `SPRING_DATASOURCE_PASSWORD` | `password` | Database password |
| `SPRING_SECURITY_USER_NAME` | `admin` | Application username |
| `SPRING_SECURITY_USER_PASSWORD` | `admin123` | Application password |
| `SPRING_PROFILES_ACTIVE` | `prod` | Active Spring profile |

### Override with Environment File

Create a `.env` file in the project root:

```env
SPRING_DATASOURCE_USERNAME=myuser
SPRING_DATASOURCE_PASSWORD=mysecretpassword
SPRING_SECURITY_USER_NAME=myadmin
SPRING_SECURITY_USER_PASSWORD=mysecretadminpass
```

Then run with:
```bash
docker-compose --env-file .env up --build
```

## Development vs Production

### Development Mode
- Uses the default `application.properties`
- Shows SQL queries
- More verbose logging
- DDL auto-update enabled

### Production Mode (Docker)
- Uses `application-prod.properties`
- Optimized for containerized environment
- Connection pooling configured
- Security hardened
- Health checks enabled

## Useful Commands

```bash
# Stop all services
docker-compose down

# Stop and remove volumes (deletes database data)
docker-compose down -v

# View service status
docker-compose ps

# View logs for specific service
docker-compose logs postgres
docker-compose logs app

# Access database directly
docker exec -it luchotest-postgres psql -U luchotest -d luchotestdb

# Access application container
docker exec -it luchotest-app sh

# Rebuild only the app (after code changes)
docker-compose build app
docker-compose up app
```

## Troubleshooting

### Application won't start
1. Check if PostgreSQL is running: `docker-compose logs postgres`
2. Verify environment variables are set correctly
3. Check application logs: `docker-compose logs app`

### Database connection issues
1. Wait for PostgreSQL to be fully ready (health check)
2. Verify database credentials
3. Check if database `luchotestdb` exists

### Port conflicts
If ports 8080 or 5432 are already in use, modify the `docker-compose.yml` file:

```yaml
services:
  postgres:
    ports:
      - "5433:5432"  # Use port 5433 instead
  app:
    ports:
      - "8081:8080"  # Use port 8081 instead
```

## Health Monitoring

The application includes built-in health checks:

- **Docker Health Check**: Automatically monitors container health
- **Spring Actuator**: Provides detailed health information at `/actuator/health`
- **Database Health**: Checks PostgreSQL connectivity

```bash
# Check application health
curl http://localhost:8080/actuator/health

# Check Docker container health
docker inspect --format='{{.State.Health.Status}}' luchotest-app
```