# Architecture Decision Log

## Overview

This document records the major architectural and technical decisions made during the development of the Digital Banking Backend project.

Each decision includes the rationale behind the chosen approach and, where applicable, the alternatives that were considered.

---

# Decision 1

## Use Java as the Programming Language

### Decision

Use Java for backend development.

### Reason

- Strong object-oriented programming support.
- Mature ecosystem.
- Enterprise industry standard.
- Excellent support for Spring Framework.
- High performance and scalability.

---

# Decision 2

## Use Spring Boot

### Decision

Use Spring Boot as the backend framework.

### Reason

- Rapid application development.
- Auto configuration.
- Embedded Tomcat server.
- Excellent integration with Spring ecosystem.
- Production-ready features.

### Alternatives Considered

- Jakarta EE
- Micronaut
- Quarkus

Spring Boot was selected because of its enterprise adoption and extensive documentation.

---

# Decision 3

## Use PostgreSQL

### Decision

Use PostgreSQL as the relational database.

### Reason

- Open source.
- ACID compliant.
- Excellent SQL support.
- Reliable transaction processing.
- Widely used in enterprise applications.

### Alternatives Considered

- MySQL
- Oracle Database
- SQL Server

---

# Decision 4

## Use Spring Data JPA

### Decision

Use Spring Data JPA with Hibernate.

### Reason

- Reduces boilerplate code.
- Simplifies CRUD operations.
- Automatic query generation.
- Easy integration with Spring Boot.

Example

```
findByEmail()

findByAccountNumber()

findByCustomer()
```

---

# Decision 5

## Use Hibernate

### Decision

Hibernate will act as the ORM implementation.

### Reason

- Maps Java objects to database tables.
- Eliminates manual SQL for common operations.
- Supports relationships.
- Handles entity lifecycle.

---

# Decision 6

## Use JWT Authentication

### Decision

Use JSON Web Tokens (JWT) for authentication.

### Reason

- Stateless authentication.
- Suitable for REST APIs.
- Lightweight.
- Scalable.

### Workflow

```
Login

↓

Generate JWT

↓

Client Stores Token

↓

Protected APIs
```

---

# Decision 7

## Use BCrypt Password Hashing

### Decision

Encrypt passwords using BCrypt.

### Reason

- Passwords are never stored in plain text.
- Industry standard.
- Built-in salt generation.
- Secure one-way hashing.

---

# Decision 8

## Use Layered Architecture

### Decision

Separate the application into layers.

Architecture

```
Controller

↓

Service

↓

Repository

↓

Database
```

### Reason

- Separation of concerns.
- Easier maintenance.
- Better scalability.
- Easier testing.
- Enterprise standard.

---

# Decision 9

## Use DTO Pattern

### Decision

Expose DTOs instead of entities.

### Reason

- Prevent exposing database entities.
- Better API design.
- Input validation.
- Easier future changes.

Examples

```
RegisterRequest

DepositRequest

TransferResponse
```

---

# Decision 10

## Use Global Exception Handling

### Decision

Handle application exceptions centrally.

### Reason

- Consistent API responses.
- Cleaner controllers.
- Better maintainability.

Technology

```
@ControllerAdvice
```

---

# Decision 11

## Use BigDecimal for Financial Calculations

### Decision

Use BigDecimal instead of floating-point types.

### Reason

Financial calculations require precision.

Avoid

```
double
```

Use

```
BigDecimal
```

Operations Used

- add()
- subtract()
- compareTo()

---

# Decision 12

## Use @Transactional

### Decision

Wrap financial operations in database transactions.

### Used In

- Deposit
- Withdraw
- Transfer

### Reason

Ensures all database operations either succeed together or roll back together.

Example

```
Transfer

↓

Debit Sender

↓

Credit Receiver

↓

Save Transactions

↓

Commit
```

---

# Decision 13

## Separate User and Customer

### Decision

Authentication data and personal information are stored in different entities.

Reason

Authentication

```
User
```

Business Data

```
Customer
```

Benefits

- Better security.
- Clear separation of responsibilities.
- Easier future enhancements.

---

# Decision 14

## Account Ownership Validation

### Decision

Customers can access only their own accounts.

Reason

Prevents unauthorized access.

Implemented Using

```
getCustomerAccount()
```

---

# Decision 15

## Record Every Financial Transaction

### Decision

Every deposit, withdrawal, and transfer creates a transaction record.

Reason

- Audit trail.
- Banking history.
- Financial reporting.
- Statement generation.

---

# Decision 16

## Use Feature Branch Workflow

### Decision

Develop every major feature in a dedicated Git branch.

Workflow

```
Feature Branch

↓

Develop

↓

Commit

↓

Push

↓

Merge

↓

Main
```

Benefits

- Safe development.
- Easier code review.
- Better version control.

---

# Decision 17

## Follow REST API Principles

### Decision

Expose functionality through RESTful APIs.

Reason

- Standard web architecture.
- Easy frontend integration.
- Stateless communication.

Examples

```
GET

POST

PUT
```

---

# Decision 18

## Use Bean Validation

### Decision

Validate incoming requests using Jakarta Bean Validation.

Examples

- @NotNull
- @NotBlank
- @Email
- @DecimalMin

Reason

- Cleaner controllers.
- Automatic validation.
- Consistent error handling.

---

# Decision 19

## Automatic Timestamp Management

### Decision

Maintain audit timestamps for important entities.

Fields

- createdAt
- updatedAt

Benefits

- Auditing.
- Reporting.
- Transaction history.
- Debugging.

---

# Decision 20

## Modular Package Structure

### Decision

Organize source code by responsibility.

```
controller

service

repository

entity

dto

security

exception

config
```

Reason

- Better organization.
- Easier navigation.
- Enterprise project structure.

---

# Summary

The Digital Banking Backend follows enterprise software engineering practices by adopting a layered architecture, secure authentication, transactional financial operations, clean package organization, and modern Java development principles. Every architectural decision has been made with maintainability, scalability, security, and code readability in mind.