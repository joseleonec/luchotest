# Hexagonal Architecture Refactoring Plan

## Overview
This document outlines the refactoring of the current monolithic Spring Boot application into a **Hexagonal Architecture** (also known as Ports and Adapters).

## Current Structure Analysis
The project currently follows a traditional layered architecture:
- **Controller Layer**: REST endpoints
- **Service Layer**: Business logic
- **Persistence Layer**: JPA entities and repositories
- **DTO Layer**: Data transfer objects
- **Mapper Layer**: Entity-DTO conversion

## Target Hexagonal Architecture

### Core Principles
1. **Domain-Centric**: Business logic is independent of external concerns
2. **Dependency Inversion**: Dependencies point inward toward the domain
3. **Testability**: Easy to test business logic in isolation
4. **Flexibility**: Easy to swap implementations (databases, APIs, etc.)

### Package Structure
```
co.com.sofka.luchotest/
├── domain/                          # CORE - Business Logic (no external dependencies)
│   ├── model/                       # Domain entities (pure POJOs)
│   │   ├── Cliente.java
│   │   ├── Persona.java
│   │   ├── Cuenta.java
│   │   └── Movimiento.java
│   ├── port/                        # Interfaces defining contracts
│   │   ├── in/                      # Input ports (use cases)
│   │   │   ├── ClienteUseCase.java
│   │   │   ├── CuentaUseCase.java
│   │   │   ├── MovimientoUseCase.java
│   │   │   └── EstadoCuentaUseCase.java
│   │   └── out/                     # Output ports (repositories, external services)
│   │       ├── ClienteRepositoryPort.java
│   │       ├── CuentaRepositoryPort.java
│   │       └── MovimientoRepositoryPort.java
│   ├── service/                     # Domain services (business logic implementation)
│   │   ├── ClienteDomainService.java
│   │   ├── CuentaDomainService.java
│   │   ├── MovimientoDomainService.java
│   │   └── EstadoCuentaDomainService.java
│   └── exception/                   # Domain exceptions
│       ├── SaldoInsuficienteException.java
│       └── ResourceAlreadyExistsException.java
│
├── application/                     # APPLICATION LAYER - Orchestration
│   ├── usecase/                     # Use case implementations
│   │   ├── ClienteUseCaseImpl.java
│   │   ├── CuentaUseCaseImpl.java
│   │   ├── MovimientoUseCaseImpl.java
│   │   └── EstadoCuentaUseCaseImpl.java
│   └── mapper/                      # Domain-DTO mappers
│       ├── ClienteMapper.java
│       ├── CuentaMapper.java
│       └── MovimientoMapper.java
│
├── adapter/                         # ADAPTERS - External interfaces
│   ├── in/                          # Inbound adapters (driving)
│   │   ├── web/                     # REST controllers
│   │   │   ├── ClienteController.java
│   │   │   ├── CuentaController.java
│   │   │   ├── MovimientoController.java
│   │   │   └── ReportesController.java
│   │   └── dto/                     # Request/Response DTOs
│   │       ├── request/
│   │       │   ├── ClienteCreateDTO.java
│   │       │   ├── CuentaCreateDTO.java
│   │       │   ├── MovimientoCreateDTO.java
│   │       │   └── ReporteRequestDTO.java
│   │       └── response/
│   │           ├── ClienteResponseDTO.java
│   │           ├── CuentaDTO.java
│   │           ├── MovimientoDTO.java
│   │           ├── EstadoCuentaDTO.java
│   │           └── ApiErrorResponse.java
│   └── out/                         # Outbound adapters (driven)
│       └── persistence/             # Database adapter
│           ├── entity/              # JPA entities
│           │   ├── ClienteEntity.java
│           │   ├── PersonaEntity.java
│           │   ├── CuentaEntity.java
│           │   └── MovimientoEntity.java
│           ├── repository/          # Spring Data repositories
│           │   ├── ClienteJpaRepository.java
│           │   ├── CuentaJpaRepository.java
│           │   └── MovimientoJpaRepository.java
│           ├── adapter/             # Repository port implementations
│           │   ├── ClienteRepositoryAdapter.java
│           │   ├── CuentaRepositoryAdapter.java
│           │   └── MovimientoRepositoryAdapter.java
│           └── mapper/              # Entity-Domain mappers
│               ├── ClientePersistenceMapper.java
│               ├── CuentaPersistenceMapper.java
│               └── MovimientoPersistenceMapper.java
│
├── config/                          # Configuration
│   ├── SecurityConfig.java
│   ├── RestExceptionHandler.java
│   └── CustomErrorMessages.java
│
└── LuchotestApplication.java        # Main application
```

## Refactoring Steps

### Phase 1: Create Domain Layer (Core)
1. Create domain model classes (pure POJOs without JPA annotations)
2. Define input ports (use case interfaces)
3. Define output ports (repository interfaces)
4. Move domain exceptions

### Phase 2: Create Application Layer
1. Implement use cases
2. Create domain-DTO mappers

### Phase 3: Create Outbound Adapters
1. Keep existing JPA entities
2. Create repository adapters implementing output ports
3. Create entity-domain mappers

### Phase 4: Create Inbound Adapters
1. Refactor controllers to use input ports (use cases)
2. Organize DTOs into request/response packages
3. Update mappers

### Phase 5: Update Configuration
1. Update Spring configuration
2. Ensure proper dependency injection
3. Update tests

## Benefits of This Refactoring

1. **Independence**: Domain logic is independent of frameworks
2. **Testability**: Can test business logic without Spring context
3. **Flexibility**: Easy to change database or add new interfaces (GraphQL, gRPC)
4. **Maintainability**: Clear separation of concerns
5. **Scalability**: Easy to extract to microservices later

## Migration Strategy

- **Incremental**: Refactor one bounded context at a time (Cliente → Cuenta → Movimiento)
- **Parallel**: Keep old code until new structure is verified
- **Testing**: Ensure all tests pass after each phase
