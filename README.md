# 🏦 Digital Banking Backend

A secure and scalable **Digital Banking Backend** built with **Java**, **Spring Boot**, and **PostgreSQL**. This project simulates the core functionalities of a modern banking system, including authentication, customer management, account management, and financial transactions.

The application follows enterprise backend development practices such as layered architecture, JWT authentication, Spring Security, JPA/Hibernate, DTO pattern, transactional processing, and Git feature branch workflow.

---

## 🚀 Features

### 🔐 Authentication
- User Registration
- User Login
- JWT Authentication
- Password Encryption (BCrypt)
- Change Password
- Forgot Password
- Reset Password

### 👤 Customer Management
- View Customer Profile
- Update Customer Profile

### 🏦 Account Management
- Create Account
- View All Accounts
- View Account Details
- Balance Enquiry

### 💰 Financial Transactions
- Deposit Money
- Withdraw Money
- Fund Transfer
- Transaction History

---

# 🛠 Technology Stack

| Category              |          Technology     |
|-----------------------|-------------------------|
| Language              |   Java                  |
| Backend Framework     | Spring Boot             |
| Web Framework         | Spring MVC              |
| Security              | Spring Security         |
| Authentication        | JWT                     |
| Password Encryption   | BCrypt                  |
| ORM                   | Hibernate               |
| Persistence           | Spring Data JPA         |
| Database              | PostgreSQL              |
| Build Tool            | Maven                   |
| Validation            | Jakarta Bean Validation |
| Boilerplate Reduction | Lombok                  |
| API Testing           | Postman                 |
| Version Control       | Git                     |
| Repository Hosting    | GitHub                  |
| IDE                   | Visual Studio Code      |
|-----------------------|-------------------------|
---

# 🏗 Architecture

The project follows a layered architecture.


                    Client
                       │
                       ▼
               REST API (HTTP/JSON)
                       │
                       ▼
                 Controller Layer
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

# 📂 Project Structure


digital-banking/
│
├── docs/
│   ├── api-documentation.md
│   ├── architecture.md
│   ├── database-schema.md
│   ├── decision-log.md
│   ├── er-diagram.md
│   ├── git-workflow.md
│   ├── project-overview.md
│   ├── security.md
│   ├── technology-stack.md
│   ├── sprint0.md
│   ├── sprint1.md
│   ├── sprint2.md
│   ├── sprint3.md
│   └── sprint4.md
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
│
├── pom.xml
└── README.md
```


# 🗄 Database Design

The application uses four core entities.

```text
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

- User → Customer (1:1)
- Customer → Account (1:N)
- Account → Transaction (1:N)

---

# 📡 REST APIs

## Authentication

| Method  | Endpoint                       |
|---------|--------------------------------|
| POST    | `/api/v1/auth/register`        |
| POST    | `/api/v1/auth/login`           |
| POST    | `/api/v1/auth/change-password` |
| POST    | `/api/v1/auth/forgot-password` |
| POST    | `/api/v1/auth/reset-password`  |

---

## Customer

| Method  | Endpoint               |
|---------|------------------------|
| GET     | `/api/v1/customers/me` |
| PUT     | `/api/v1/customers/me` |

---

## Accounts

| Method  | Endpoint                                   |
|---------|--------------------------------------------|
| POST    | `/api/v1/accounts`                         |
| GET     | `/api/v1/accounts`                         |
| GET     | `/api/v1/accounts/{accountNumber}`         |
| GET     | `/api/v1/accounts/{accountNumber}/balance` |

---

## Transactions

| Method  |    Endpoint                                     |
|---------|-------------------------------------------------|
| POST    | `/api/v1/accounts/{accountNumber}/deposit`      |
| POST    | `/api/v1/accounts/{accountNumber}/withdraw`     |
| POST    | `/api/v1/accounts/transfer`                     |
| GET     | `/api/v1/accounts/{accountNumber}/transactions` |

---

# 🔒 Security

The application uses:

- JWT Authentication
- Spring Security
- BCrypt Password Encryption
- Stateless Authentication
- Account Ownership Validation
- Bean Validation
- Global Exception Handling

---

# 📖 Documentation

Detailed project documentation is available in the `docs/` directory.

- Project Overview
- Technology Stack
- System Architecture
- API Documentation
- Database Schema
- Security
- ER Diagram
- Decision Log
- Git Workflow
- Sprint Documentation

---

# ▶️ Getting Started

## Prerequisites

- Java 21
- Maven
- PostgreSQL
- Git
- VS Code (or IntelliJ IDEA)
- Postman

---

## Clone the Repository

```bash
git clone https://github.com/abhinavmanojpr/digital-banking.git
```

---

## Navigate to the Project

```bash
cd digital-banking
```

---

## Configure Database

Update the database configuration in:

```text
src/main/resources/application.properties
```

Example

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/digital_banking
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
```

---

## Run the Application

Using Maven

```bash
mvn spring-boot:run
```

Or

Run

```
DigitalBankingApplication.java
```

from your IDE.

---

# ✅ Current Project Status

| Sprint | Status |
|----------|--------|
| Sprint 0 | ✅ Completed |
| Sprint 1 | ✅ Completed |
| Sprint 2 | ✅ Completed |
| Sprint 3 | ✅ Completed |
| Sprint 4 | ✅ Completed |


---

# 🚀 Future Enhancements

- Account Lifecycle Management
- Admin Module
- Role-Based Access Control (RBAC)
- Swagger / OpenAPI
- Unit Testing
- Integration Testing
- Docker
- AWS Deployment
- CI/CD Pipeline
- Monitoring & Logging
- Email Notifications
- Audit Logging

---

# 📚 Learning Outcomes

This project demonstrates practical experience with:

- Java
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- PostgreSQL
- REST API Development
- Financial Transaction Processing
- Git Feature Branch Workflow
- Enterprise Software Architecture

---

# 👨‍💻 Author

**Abhinav M**

Digital Banking Backend Project

Built as a hands-on project to learn enterprise backend development using Java, Spring Boot, and PostgreSQL.

---

# ⭐ Acknowledgements

This project was developed as a practical learning experience to understand modern backend development, secure REST APIs, relational database design, and enterprise software engineering practices.