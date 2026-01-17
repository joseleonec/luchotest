# Luchotest - Spring Boot Application

A modern Spring Boot application built with Java 21, featuring REST API endpoints for client management, PostgreSQL database integration, and comprehensive security.

## 🚀 Features

- **Spring Boot 4.0.1** - Latest Spring Boot framework
- **Java 21** - Modern Java features and performance
- **PostgreSQL** - Production-ready database
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Database abstraction layer
- **MapStruct** - Type-safe bean mapping
- **Docker Support** - Containerized deployment
- **Comprehensive Testing** - Integration tests with TestContainers approach
- **Health Monitoring** - Actuator endpoints for monitoring

## 📋 Prerequisites

- **Java 21** or higher
- **Maven 3.6+** 
- **PostgreSQL 12+** (or Docker for containerized setup)
- **Docker** (optional, for containerized deployment)

## 🛠️ Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Spring Boot | 4.0.1 | Application Framework |
| Java | 21 | Programming Language |
| Maven | 3.6+ | Build Tool |
| PostgreSQL | 15+ | Database |
| Spring Security | 6.x | Security Framework |
| Spring Data JPA | 3.x | Data Access Layer |
| Hibernate | 7.x | ORM Framework |
| MapStruct | 1.6.3 | Bean Mapping |
| Lombok | Latest | Code Generation |
| JUnit 5 | Latest | Testing Framework |

## 🏗️ Project Structure

```
luchotest/
├── src/
│   ├── main/
│   │   ├── java/co/com/sofka/luchotest/
│   │   │   ├── controller/     # REST Controllers
│   │   │   ├── service/        # Business Logic
│   │   │   ├── repository/     # Data Access
│   │   │   ├── entity/         # JPA Entities
│   │   │   ├── dto/            # Data Transfer Objects
│   │   │   ├── mapper/         # MapStruct Mappers
│   │   │   ├── config/         # Configuration Classes
│   │   │   └── LuchotestApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── application-prod.properties
│   └── test/
│       ├── java/               # Test Classes
│       └── resources/          # Test Resources
├── db/
│   ├── BaseDatos.sql           # Database Schema
│   └── docker-compose.yml     # Database Setup
├── Dockerfile                 # Multi-stage Docker build
├── docker-compose.yml         # Full application stack
└── pom.xml                    # Maven Configuration
```

## ⚡ Quick Start

### 1. Clone the Repository

```bash
git clone <repository-url>
cd luchotest
```

### 2. Database Setup

#### Option A: Using Docker (Recommended)
```bash
# Start PostgreSQL with Docker Compose
cd db
docker-compose up -d
```

#### Option B: Local PostgreSQL
1. Install PostgreSQL 15+
2. Create database: `luchotestdb`
3. Run the SQL script: `db/BaseDatos.sql`

### 3. Environment Configuration

Set the following environment variables:

```bash
# Database Configuration
export SPRING_DATASOURCE_USERNAME=your_db_user
export SPRING_DATASOURCE_PASSWORD=your_db_password

# Security Configuration  
export SPRING_SECURITY_USER_NAME=admin
export SPRING_SECURITY_USER_PASSWORD=your_secure_password
```

Or create a `.env` file in the project root:

```env
SPRING_DATASOURCE_USERNAME=luchotest
SPRING_DATASOURCE_PASSWORD=password
SPRING_SECURITY_USER_NAME=admin
SPRING_SECURITY_USER_PASSWORD=admin123
```

### 4. Build and Run

```bash
# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run

# Or run the JAR directly
java -jar target/luchotest-0.0.1-SNAPSHOT.jar
```

### 5. Verify Installation

- **Application**: http://localhost:8080
- **Health Check**: http://localhost:8080/actuator/health
- **API Documentation**: http://localhost:8080/actuator (when available)

## 🐳 Docker Deployment

### Quick Docker Setup

```bash
# Build and run everything (app + database)
docker-compose up --build

# Run in background
docker-compose up --build -d

# View logs
docker-compose logs -f app
```

### Docker Features

- **Multi-stage build** with test validation
- **Production-optimized** runtime image
- **Health checks** for reliability
- **Non-root user** for security
- **Alpine Linux** for minimal footprint

## 🧪 Testing

### Run Tests

```bash
# Run all tests
./mvnw test

# Run specific test class
./mvnw test -Dtest=ClienteControllerIntegrationTest

# Run with coverage
./mvnw test jacoco:report
```

### Test Structure

- **Unit Tests**: Service and component testing
- **Integration Tests**: Full HTTP request/response testing
- **Test Database**: H2 in-memory database for testing

## 📡 API Endpoints

### Cliente Management

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | `/clientes` | Create new cliente | ✅ |
| GET | `/clientes/{id}` | Get cliente by ID | ✅ |
| PUT | `/clientes/{id}` | Update cliente | ✅ |
| DELETE | `/clientes/{id}` | Delete cliente | ✅ |

### Monitoring

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/actuator/health` | Application health | ❌ |
| GET | `/actuator/info` | Application info | ❌ |

### Example API Usage

```bash
# Create a new cliente
curl -X POST http://localhost:8080/clientes \
  -u admin:admin123 \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan Pérez",
    "genero": "Masculino",
    "edad": 30,
    "identificacion": "12345678",
    "direccion": "Calle 123",
    "telefono": "555-1234",
    "clienteId": "CLI-001",
    "contrasena": "password123",
    "estado": "Activo"
  }'

# Get cliente by ID
curl -X GET http://localhost:8080/clientes/1 \
  -u admin:admin123
```

## 🔧 Configuration

### Application Properties

#### Development (`application.properties`)
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/luchotestdb
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

#### Production (`application-prod.properties`)
```properties
spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
```

### Security Configuration

The application uses Spring Security with Basic Authentication:
- Default user: `admin`
- Password: Set via `SPRING_SECURITY_USER_PASSWORD` environment variable

## 📊 Monitoring and Health

### Health Checks

```bash
# Application health
curl http://localhost:8080/actuator/health

# Detailed health (with auth)
curl -u admin:admin123 http://localhost:8080/actuator/health
```

### Logs

```bash
# Docker logs
docker-compose logs -f app

# Local logs
tail -f logs/application.log
```

## 🚨 Troubleshooting

### Common Issues

1. **Database Connection Failed**
   ```bash
   # Check if PostgreSQL is running
   docker-compose ps
   
   # Verify credentials
   echo $SPRING_DATASOURCE_USERNAME
   ```

2. **Port Already in Use**
   ```bash
   # Check what's using port 8080
   netstat -tlnp | grep 8080
   
   # Or change port in application.properties
   server.port=8081
   ```

3. **Tests Failing**
   ```bash
   # Run with debug output
   ./mvnw test -X
   
   # Skip tests temporarily
   ./mvnw spring-boot:run -DskipTests
   ```

### Performance Tuning

```bash
# Increase JVM memory
export JAVA_OPTS="-Xms512m -Xmx1024m"

# Enable JVM metrics
export JAVA_OPTS="$JAVA_OPTS -Dmanagement.metrics.export.jvm.enabled=true"
```

## 🤝 Contributing

### Development Workflow

1. **Fork** the repository
2. **Create** a feature branch: `git checkout -b feature/amazing-feature`
3. **Commit** your changes: `git commit -m 'Add amazing feature'`
4. **Run tests**: `./mvnw test`
5. **Push** to the branch: `git push origin feature/amazing-feature`
6. **Open** a Pull Request

### Code Style

- Follow **Java Conventions**
- Use **Lombok** for boilerplate code
- Write **comprehensive tests**
- Document **public APIs**
- Use **meaningful commit messages**

### Pre-commit Checklist

- [ ] Tests pass: `./mvnw test`
- [ ] Code compiles: `./mvnw compile`
- [ ] Docker builds: `docker build -t luchotest .`
- [ ] Documentation updated

## 📚 Documentation

- [Spring Boot Reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Docker Setup Guide](DOCKER_README.md)
- [API Documentation](docs/api.md) *(when available)*

## 📞 Support

For support and questions:

1. **Check** the troubleshooting section
2. **Search** existing issues
3. **Create** a new issue with detailed information
4. **Include** logs and configuration details

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

**Built with ❤️ using Spring Boot 4.0.1 and Java 21**

### **Build the Docker Image**

```bash
# Build the image (includes running tests)
docker build -t luchotest-app .

# Build with specific tag
docker build -t luchotest-app:latest .

# Build with version tag
docker build -t luchotest-app:0.0.1-SNAPSHOT .

# Build without cache (clean build)
docker build --no-cache -t luchotest-app .
```

### **Run the Built Image**
#### Unix/Linux/PowerShell
```bash
# Run with environment variables
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/luchotestdb \
  -e SPRING_DATASOURCE_USERNAME=postgres \
  -e SPRING_DATASOURCE_PASSWORD=postgres \
  -e SPRING_SECURITY_USER_NAME=admin \
  -e SPRING_SECURITY_USER_PASSWORD=admin \
  luchotest-app
```

#### Windows CMD
```cmd
# Run with environment variables
docker run -p 8080:8080 ^
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/luchotestdb ^
  -e SPRING_DATASOURCE_USERNAME=postgres ^
  -e SPRING_DATASOURCE_PASSWORD=postgres ^
  -e SPRING_SECURITY_USER_NAME=admin ^
  -e SPRING_SECURITY_USER_PASSWORD=admin ^
  luchotest-app
```

#### Single Line (All Platforms)
```bash
docker run -p 8080:8080 -e SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/luchotestdb -e SPRING_DATASOURCE_USERNAME=postgres -e SPRING_DATASOURCE_PASSWORD=postgres -e SPRING_SECURITY_USER_NAME=admin -e SPRING_SECURITY_USER_PASSWORD=admin luchotest-app
```
