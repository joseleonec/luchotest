# Luchotest - Spring Boot Application

A modern banking application backend built with Spring Boot and Java 21. It provides a REST API for managing clients, accounts, and transactions, featuring PostgreSQL integration and Docker containerization.

## 🚀 Features

- **Client Management**: Create, read, update, and delete client information.
- **Account Management**: Manage bank accounts (Savings/Checking).
- **Transaction Processing**: Handle deposits, withdrawals, and balance updates.
- **Reporting**: Generate account statements and activity reports.
- **Security**: Basic Authentication implemented with Spring Security.
- **Docker Support**: Full containerization for application and database.
- **Testing**: Comprehensive integration tests.

## 🛠️ Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 21 | Programming Language |
| Spring Boot | 4.0.1 | Application Framework |
| PostgreSQL | Latest | Database |
| Spring Data JPA | - | Data Access / ORM |
| Spring Security | - | Authentication |
| MapStruct | - | Object Mapping |
| Docker | - | Containerization |

## 🏗️ Project Structure

```text
luchotest/
├── src/
│   ├── main/
│   │   ├── java/co/com/sofka/luchotest/
│   │   │   ├── config/         # Security & Global configs
│   │   │   ├── controller/     # API Endpoints
│   │   │   ├── dto/            # Data Transfer Objects
│   │   │   ├── exceptions/     # Custom Exceptions
│   │   │   ├── mapper/         # DTO-Entity Mappers
│   │   │   ├── persistence/    # Persistence Layer
│   │   │   │   ├── entity/     # Database Entities
│   │   │   │   └── repository/ # JPA Repositories
│   │   │   └── service/        # Business Logic
│   │   └── resources/
│   │       ├── db/             # Database initialization scripts
│   │       ├── application.properties
│   │       └── application-prod.properties
├── db/                         # Database specific configurations
│   ├── BaseDatos.sql
│   └── docker-compose.yml
├── Dockerfile                  # Application Dockerfile
├── docker-compose.yml          # Main composition file
└── pom.xml
```

## 📋 Prerequisites

- **Java 21** Development Kit (JDK)
- **Maven** 3.8+
- **Docker** & **Docker Compose** (Recommended)

## ⚡ Getting Started

### 1. Clone the Repository

```bash
git clone <repository-url>
cd luchotest
```

### 2. Environment Configuration

To protect sensitive data, this application uses environment variables. You must set these variables before running the application.

| Variable | Description | Example |
|----------|-------------|---------|
| `SPRING_DATASOURCE_USERNAME` | Database username | `postgres` |
| `SPRING_DATASOURCE_PASSWORD` | Database password | `mysecretpassword` |
| `SPRING_SECURITY_USER_NAME` | API Admin username | `admin` |
| `SPRING_SECURITY_USER_PASSWORD` | API Admin password | `admin123` |

**Option A: Using an `.env` file (Docker)**
Create a `.env` file in the root directory (variables are automatically picked up by docker-compose).

**Option B: Terminal Export (Local Run)**
```bash
# Linux/Mac
export SPRING_DATASOURCE_USERNAME=postgres
export SPRING_DATASOURCE_PASSWORD=postgres
export SPRING_SECURITY_USER_NAME=admin
export SPRING_SECURITY_USER_PASSWORD=admin

# Windows PowerShell
$env:SPRING_DATASOURCE_USERNAME="postgres"
$env:SPRING_DATASOURCE_PASSWORD="postgres"
$env:SPRING_SECURITY_USER_NAME="admin"
$env:SPRING_SECURITY_USER_PASSWORD="admin"
```

### 3. Database Setup

**Using Docker (Recommended):**
Start a standalone PostgreSQL instance using the configuration in the `db` folder:

```bash
cd db
docker-compose up -d
```
This starts PostgreSQL on port `5432` and initializes the schema using `BaseDatos.sql`.

**Local Installation:**
1. Install PostgreSQL.
2. Create a database named `luchotestdb`.
3. Execute the script `db/BaseDatos.sql` to create tables.

### 4. Running the Application

**Run with Maven:**
Ensure the environment variables are set and the database is running.

```bash
./mvnw spring-boot:run
```

**Run with Docker Compose (Full Stack):**
This will build the app image and run it alongside the database.

```bash
docker-compose up --build
```

The application will be accessible at `http://localhost:8080`.

## 🧪 Testing

Run the integration tests using Maven:

```bash
./mvnw test
```

## 📡 API Documentation

A **Postman Collection** is included to facilitate testing.
-   **File**: `src/main/resources/luchotest-sofka.postman_collection.json`
-   **Usage**: Import into Postman. It includes pre-configured requests for all endpoints.

### Main Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/clientes` | List all clients |
| `POST` | `/clientes` | Create a new client |
| `POST` | `/cuentas` | Create a new account |
| `POST` | `/movimientos` | Register a transaction |
| `GET` | `/reportes` | Generic reports |

*Note: Endpoints require Basic Authentication using the credentials set in `SPRING_SECURITY_USER_NAME` and `SPRING_SECURITY_USER_PASSWORD`.*

## 🐳 Docker Deployment

To build and run the Docker image manually:

```bash
# Build
docker build -t joseleon97/sofkabacktest .

# Run
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/luchotestdb \
  -e SPRING_DATASOURCE_USERNAME=postgres \
  -e SPRING_DATASOURCE_PASSWORD=postgres \
  -e SPRING_SECURITY_USER_NAME=admin \
  -e SPRING_SECURITY_USER_PASSWORD=admin \
  joseleon97/sofkabacktest
```
#### Run remote image

```bash
docker run -p 8080:8080 -e SPRING_DATASOURCE_URL=jdbc:postgresql://ep-gentle-leaf-aho23oym-pooler.c-3.us-east-1.aws.neon.tech/neondb -e SPRING_DATASOURCE_USERNAME=neondb_owner -e SPRING_DATASOURCE_PASSWORD=npg_MGfbezui9J5t -e SPRING_SECURITY_USER_NAME=admin -e SPRING_SECURITY_USER_PASSWORD=admin joseleon97/sofkabacktest:latest
```

## 📄 License

This project is licensed under the MIT License.
