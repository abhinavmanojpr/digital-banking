# Digital Banking Backend

## Project Overview

Digital Banking Backend is a RESTful banking application developed using **Java**, **Spring Boot**, and **PostgreSQL**. The project simulates the core functionalities of a modern digital banking system, including secure authentication, customer management, account management, and financial transactions.

The application follows a layered architecture and incorporates enterprise backend development practices such as JWT-based authentication, Spring Security, JPA/Hibernate, DTOs, exception handling, and transactional database operations.

---

# Project Objectives

The primary objectives of this project are:

- Build a secure backend using Spring Boot.
- Implement JWT-based authentication and authorization.
- Design a normalized relational database.
- Develop core banking operations.
- Follow clean architecture and coding standards.
- Practice enterprise software development using Git feature branch workflow.
- Prepare a production-ready backend suitable for portfolio and interview demonstrations.

---

# Features Implemented

## Authentication Module

- User Registration
- User Login
- JWT Authentication
- Password Encryption using BCrypt
- Change Password
- Forgot Password
- Reset Password

---

## Customer Module

- View Customer Profile
- Update Customer Profile

---

## Account Module

- Create Bank Account
- View All Customer Accounts
- View Account Details
- Balance Enquiry

---

## Financial Transactions

- Deposit Money
- Withdraw Money
- Fund Transfer
- Transaction History

---

# Current Modules

```
Authentication
        │
        ▼
Customer Management
        │
        ▼
Account Management
        │
        ▼
Financial Transactions
```

---

# Technology Stack

## Backend

- Java
- Spring Boot
- Spring MVC
- Spring Security
- Spring Data JPA
- Hibernate

## Database

- PostgreSQL

## Authentication

- JWT (JSON Web Token)
- BCrypt Password Encoder

## Build Tool

- Maven

## Libraries

- Lombok
- Jakarta Bean Validation

## Development Tools

- VS Code
- Postman
- Git
- GitHub

---

# Software Architecture

The project follows a layered architecture.

```
Client

↓

REST API

↓

Controller Layer

↓

Service Layer

↓

Repository Layer

↓

Hibernate (JPA)

↓

PostgreSQL
```

---

# Database Design

Current entities:

- User
- Customer
- Account
- Transaction

Relationship:

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

---

# Banking Features

- Customer Registration
- Secure Login
- Account Creation
- Deposit
- Withdraw
- Fund Transfer
- Transaction History
- Balance Enquiry

---

# Security Features

- JWT Authentication
- Password Hashing using BCrypt
- Spring Security
- Protected REST APIs
- Role-based User Authentication
- Account Ownership Validation

---

# APIs Implemented

### Authentication

- POST /api/v1/auth/register
- POST /api/v1/auth/login
- POST /api/v1/auth/change-password
- POST /api/v1/auth/forgot-password
- POST /api/v1/auth/reset-password

### Customer

- GET /api/v1/customers/me
- PUT /api/v1/customers/me

### Account

- POST /api/v1/accounts
- GET /api/v1/accounts
- GET /api/v1/accounts/{accountNumber}
- GET /api/v1/accounts/{accountNumber}/balance

### Transactions

- POST /api/v1/accounts/{accountNumber}/deposit
- POST /api/v1/accounts/{accountNumber}/withdraw
- POST /api/v1/accounts/transfer
- GET /api/v1/accounts/{accountNumber}/transactions

---

# Project Structure

```
digital-banking/
│
├── docs/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
│
├── pom.xml
└── README.md
```

---

# Project Status

| Sprint | Status |
|----------|--------|
| Sprint 0 | ✅ Completed |
| Sprint 1 | ✅ Completed |
| Sprint 2 | ✅ Completed |
| Sprint 3 | ✅ Completed |
| Sprint 4 | ✅ Completed |

---

# Future Enhancements

- Account Lifecycle Management
- Admin Module
- Swagger/OpenAPI Documentation
- Unit Testing
- Integration Testing
- Docker Support
- AWS Deployment
- CI/CD Pipeline
- Email Notifications
- Audit Logging

---

# Author

**Abhinav M**

Digital Banking Backend Project

Built using Java, Spring Boot, and PostgreSQL.