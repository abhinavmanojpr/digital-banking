# System Architecture

## Overview

The Digital Banking Backend follows a **Layered Architecture**, which separates responsibilities into different layers to improve maintainability, scalability, testability, and readability.

Each layer has a single responsibility and communicates only with adjacent layers.

---

# High-Level Architecture

```
                    Client
                       │
                       ▼
               REST API (HTTP/JSON)
                       │
                       ▼
                 Spring MVC Controller
                       │
                       ▼
                  Service Layer
                       │
                       ▼
                Repository Layer
                       │
                       ▼
             Hibernate / Spring Data JPA
                       │
                       ▼
                  PostgreSQL Database
```

---

# Layered Architecture

```
Controller Layer

↓

Service Layer

↓

Repository Layer

↓

Database
```

Each layer has a specific responsibility.

---

# Controller Layer

Package

```
controller
```

Responsibilities

- Handle HTTP requests
- Validate request payloads
- Invoke service methods
- Return HTTP responses

Technologies

- Spring MVC
- REST Controllers
- ResponseEntity

Annotations Used

```
@RestController

@RequestMapping

@GetMapping

@PostMapping

@PutMapping

@PathVariable

@RequestBody

@Valid
```

Current Controllers

```
AuthenticationController

CustomerController

AccountController
```

---

# Service Layer

Package

```
service

service.impl
```

Responsibilities

- Business logic
- Banking rules
- Validation
- Transaction management
- Authorization

Examples

- Register User
- Deposit Money
- Withdraw Money
- Transfer Funds
- Change Password

Annotations

```
@Service

@Transactional
```

---

# Repository Layer

Package

```
repository
```

Responsibilities

- Database access
- CRUD operations
- Custom queries

Technology

Spring Data JPA

Repository Interfaces

```
UserRepository

CustomerRepository

AccountRepository

TransactionRepository
```

Extends

```
JpaRepository
```

Example

```
findByEmail()

findByPhone()

findByAccountNumber()

findByCustomer()
```

---

# Entity Layer

Package

```
entity
```

Entities

```
User

Customer

Account

Transaction
```

Purpose

Represents database tables.

Technology

- JPA
- Hibernate

Annotations

```
@Entity

@Table

@Id

@OneToOne

@OneToMany

@ManyToOne

@JoinColumn
```

---

# DTO Layer

Package

```
dto

├── request
└── response
```

Purpose

Separates API models from database entities.

Benefits

- Better security
- Cleaner APIs
- Prevents exposing entities directly
- Easier versioning

Example

```
RegisterRequest

RegisterResponse

DepositRequest

DepositResponse

TransferRequest

TransferResponse
```

---

# Security Layer

Package

```
security
```

Components

```
JwtService

JwtAuthenticationFilter

CustomUserDetailsService
```

Configuration

```
SecurityConfig
```

Responsibilities

- JWT generation
- JWT validation
- User authentication
- Authorization
- Protect REST APIs

---

# Exception Layer

Package

```
exception
```

Responsibilities

- Business exceptions
- Global exception handling
- Error responses

Examples

```
AccountNotFoundException

InsufficientBalanceException

UnauthorizedAccountAccessException

PhoneAlreadyExistsException
```

Global Handler

```
GlobalExceptionHandler
```

---

# Configuration Layer

Package

```
config
```

Contains

```
SecurityConfig
```

Purpose

Application configuration.

---

# Database Architecture

```
User
 │
 ▼
Customer
 │
 ▼
Account
 │
 ▼
Transaction
```

Relationships

```
User

↓

Customer

↓

Account

↓

Transaction
```

---

# Request Lifecycle

Example

Deposit Money

```
Client

↓

POST /deposit

↓

AccountController

↓

AccountService

↓

AccountRepository

↓

Hibernate

↓

PostgreSQL

↓

Response
```

---

# Authentication Flow

```
Login

↓

Authentication Service

↓

UserDetailsService

↓

Generate JWT

↓

Client Stores JWT

↓

Protected API

↓

JWT Filter

↓

SecurityContext

↓

Controller
```

---

# Fund Transfer Flow

```
Transfer Request

↓

Validate Sender

↓

Validate Receiver

↓

Validate Balance

↓

Debit Sender

↓

Credit Receiver

↓

Save Sender

↓

Save Receiver

↓

Save Transactions

↓

Return Response
```

Because the transfer method is annotated with

```
@Transactional
```

either every operation succeeds or all changes are rolled back.

---

# Package Structure

```
src/main/java/com/digitalbanking

├── config
├── controller
├── dto
│   ├── request
│   └── response
├── entity
├── enums
├── exception
├── repository
├── security
├── service
│   └── impl
└── DigitalBankingApplication.java
```

---

# Design Principles

The project follows the following principles.

- Layered Architecture
- Separation of Concerns
- Dependency Injection
- Repository Pattern
- DTO Pattern
- Builder Pattern
- Stateless Authentication
- Transactional Business Logic

---

# Why Layered Architecture?

Benefits

- Clean code organization
- Easy maintenance
- Better scalability
- Independent testing
- Loose coupling
- High cohesion
- Enterprise standard

---

# Current Architecture Summary

```
Client

↓

Spring Security

↓

JWT Filter

↓

Controller

↓

Service

↓

Repository

↓

Hibernate

↓

PostgreSQL
```

---

# Future Architectural Enhancements

Planned improvements

- Swagger/OpenAPI
- Docker
- Unit Testing
- Integration Testing
- Logging
- Monitoring
- CI/CD
- AWS Deployment
- Role-Based Access Control
- Email Service

---

# Conclusion

The Digital Banking Backend is designed using a layered architecture that separates presentation, business logic, persistence, and security concerns. This architecture improves maintainability, scalability, and testability while following enterprise software development practices commonly used in modern Java backend applications.