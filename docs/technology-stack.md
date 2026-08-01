# Technology Stack

## Overview

The Digital Banking Backend project is built using modern Java backend technologies and follows enterprise software development practices. The selected technology stack provides security, scalability, maintainability, and clean architecture.

---

# Technology Stack Overview

| Category | Technology |
|----------|------------|
| Programming Language | Java |
| Backend Framework | Spring Boot |
| Web Framework | Spring MVC |
| Security | Spring Security |
| Authentication | JWT |
| Password Encryption | BCrypt |
| ORM | Hibernate |
| Persistence | Spring Data JPA |
| Database | PostgreSQL |
| Build Tool | Maven |
| Validation | Jakarta Bean Validation |
| Boilerplate Reduction | Lombok |
| API Testing | Postman |
| Version Control | Git |
| Repository Hosting | GitHub |
| IDE | Visual Studio Code |

---

# Programming Language

## Java

Version Used:
- Java 24

Purpose:
- Core programming language for backend development.
- Object-Oriented Programming.
- Exception handling.
- Collection Framework.
- Streams API.
- BigDecimal for financial calculations.

Used In:
- Entities
- Controllers
- Services
- DTOs
- Exception Handling
- Security

---

# Backend Framework

## Spring Boot

Purpose:
- Rapid application development.
- Embedded Tomcat Server.
- Auto Configuration.
- Dependency Injection.
- REST API development.

Why Spring Boot?

- Reduces configuration.
- Production-ready framework.
- Large ecosystem.
- Enterprise standard.

Used In:

- REST Controllers
- Services
- Configuration
- Dependency Injection

---

# Spring MVC

Purpose:

Handles HTTP Requests and Responses.

Features Used:

- @RestController
- @GetMapping
- @PostMapping
- @PutMapping
- @RequestBody
- @PathVariable
- ResponseEntity

---

# Spring Security

Purpose

Secures all REST APIs.

Features Used

- Authentication
- Authorization
- SecurityFilterChain
- PasswordEncoder
- UserDetailsService
- SecurityContextHolder
- JWT Filter

Protected APIs

- Customer APIs
- Account APIs
- Transaction APIs

---

# JWT (JSON Web Token)

Purpose

Stateless authentication.

Workflow

```
Login

↓

Generate JWT

↓

Client stores JWT

↓

Authorization Header

↓

JWT Filter

↓

Protected APIs
```

Benefits

- Stateless
- Lightweight
- Secure
- Scalable

---

# BCrypt Password Encoder

Purpose

Encrypts user passwords before storing them in the database.

Advantages

- One-way hashing
- Salt generation
- Industry standard
- Secure password storage

---

# Spring Data JPA

Purpose

Simplifies database operations.

Features Used

- JpaRepository
- Custom Finder Methods
- CRUD Operations
- Query Generation

Example

```
findByEmail()

findByAccountNumber()

findByCustomer()
```

---

# Hibernate

Purpose

Object Relational Mapping (ORM).

Responsibilities

- Maps Java Objects to Database Tables.
- Generates SQL Queries.
- Entity Management.
- Lazy Loading.

---

# PostgreSQL

Purpose

Primary relational database.

Tables

- user
- customer
- account
- transaction

Features Used

- Primary Keys
- Foreign Keys
- Constraints
- Relationships

---

# Maven

Purpose

Project Build Tool.

Responsibilities

- Dependency Management
- Build Automation
- Packaging
- Plugin Management

Common Commands

```
mvn clean

mvn install

mvn spring-boot:run
```

---

# Lombok

Purpose

Reduces boilerplate code.

Annotations Used

- @Getter
- @Setter
- @Builder
- @NoArgsConstructor
- @AllArgsConstructor
- @RequiredArgsConstructor

Benefits

- Cleaner code
- Less boilerplate
- Better readability

---

# Jakarta Bean Validation

Purpose

Validates incoming API requests.

Annotations Used

- @NotNull
- @NotBlank
- @Email
- @DecimalMin
- @Valid

Benefits

- Input validation
- Cleaner controller code
- Automatic validation

---

# Git

Purpose

Version Control.

Workflow Used

```
Feature Branch

↓

Development

↓

Commit

↓

Push

↓

Merge

↓

Main Branch
```

Commands Used

```
git add .

git commit

git push

git pull

git merge

git checkout
```

---

# GitHub

Purpose

Remote repository hosting.

Features Used

- Repository Hosting
- Branch Management
- Pull Requests
- Version History

---

# Postman

Purpose

API Testing.

Used For

- Authentication APIs
- Customer APIs
- Account APIs
- Transaction APIs

Testing

- Success Cases
- Validation Errors
- Authorization
- Business Rule Validation

---

# Visual Studio Code

Purpose

Integrated Development Environment.

Extensions Used

- Extension Pack for Java
- Spring Boot Extension Pack
- Maven for Java
- Git Integration

---

# Financial Technologies Used

## BigDecimal

Purpose

Accurate financial calculations.

Operations Used

- add()
- subtract()
- compareTo()

Reason

Avoid floating-point precision issues.

---

# Database Transactions

## @Transactional

Purpose

Ensures atomic database operations.

Used In

- Deposit
- Withdraw
- Fund Transfer

Benefits

- Rollback on failure
- Data consistency
- ACID compliance

---

# Design Patterns

The project follows the following software design patterns.

- Layered Architecture
- Repository Pattern
- DTO Pattern
- Builder Pattern
- Dependency Injection

---

# Summary

The project combines Java enterprise technologies with Spring Boot to build a secure, scalable, and maintainable digital banking backend. The selected technology stack reflects common practices used in enterprise backend development.