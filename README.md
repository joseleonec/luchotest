# Luchotest Banking Application Repository

This repository hosts a banking application solution implemented with Spring Boot and Java 21. It demonstrates different architectural approaches, containing both a monolithic implementation and a microservices-based breakdown.

## 📂 Repository Structure

The repository is organized into three main Spring Boot projects:

### 1. [Monolito](./monolito)
A comprehensive monolithic application named **Luchotest**. It encapsulates all banking features in a single deployable unit.
- **Key Features:** Client management, account management, transaction processing.
- **Tech Stack:** Spring Boot 4.0.1, Java 21, PostgreSQL.

### 2. [Microservice Clientes](./microservice-clientes)
A dedicated microservice (**msclientes**) focusing on the management of bank clients.
- **Responsibilities:** Creating, updating, and retrieving client personal information.
- **Tech Stack:** Spring Boot, Java 21, PostgreSQL.

### 3. [Microservice Cuentas](./microservice-cuentas)
A dedicated microservice (**mscuentas**) for managing banking products.
- **Responsibilities:** Managing savings/checking accounts and processing transactions (deposits, withdrawals).
- **Tech Stack:** Spring Boot, Java 21, PostgreSQL.

## 🚀 Common Features

Across the projects, you will find implementations for:
- **RESTful APIs** for banking operations.
- **Database Integration** using PostgreSQL.
- **Containerization** support with Docker and Docker Compose.
- **Security** implementation using Spring Security.
- **Testing** with integration tests.

## 🛠️ Technology Stack

- **Language:** Java 21
- **Framework:** Spring Boot
- **Database:** PostgreSQL
- **Containerization:** Docker
- **Build Tool:** Maven

## 🏁 Getting Started

Each project contains its own `README.md` file with specific instructions on how to build and run the application.

1.  **Monolito:** See [monolito/README.md](./monolito/README.md)
2.  **Microservice Clientes:** See [microservice-clientes/README.md](./microservice-clientes/README.md)
3.  **Microservice Cuentas:** See [microservice-cuentas/README.md](./microservice-cuentas/README.md)

## 🐳 infrastructure

The repository includes a `db` folder containing PostgreSQL configuration and data persistence structures, typically used by the docker-compose setups within the projects.
