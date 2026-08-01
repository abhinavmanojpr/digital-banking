# Sprint 1 – Authentication & Security Module

## Sprint Overview

**Sprint Duration:** Authentication Development

**Sprint Goal:**

Develop a secure authentication system using Spring Security and JWT to protect banking APIs and establish the foundation for user authentication and authorization.

---

# Objectives

- Implement user registration.
- Implement user login.
- Configure Spring Security.
- Generate JWT tokens.
- Secure REST APIs.
- Encrypt passwords using BCrypt.
- Configure stateless authentication.

---

# User Stories

As a new customer, I want to:

- Register for a banking account.
- Log in securely.
- Receive a JWT token after successful authentication.
- Access protected APIs using my JWT.
- Prevent unauthorized users from accessing my data.

---

# Features Implemented

## User Registration

Implemented a registration workflow that creates:

- User
- Customer

The registration process includes:

- Email uniqueness validation
- Phone number uniqueness validation
- Password encryption
- Customer profile creation

---

## User Login

Implemented login functionality using:

- Email
- Password

On successful authentication:

- User credentials are verified.
- JWT token is generated.
- JWT is returned to the client.

---

## Spring Security

Configured Spring Security to:

- Disable CSRF
- Use Stateless Sessions
- Authenticate using JWT
- Protect secured endpoints
- Allow public authentication endpoints

---

## JWT Authentication

Implemented:

- JWT generation
- JWT validation
- JWT parsing
- JWT Authentication Filter

Authentication Flow

```
Login

↓

Generate JWT

↓

Client Stores Token

↓

Protected Request

↓

JWT Filter

↓

SecurityContext

↓

Controller
```

---

## Password Security

Passwords are encrypted using

```
BCryptPasswordEncoder
```

Passwords are never stored in plain text.

---

## Protected Endpoints

Public APIs

```
POST /api/v1/auth/register

POST /api/v1/auth/login

POST /api/v1/auth/forgot-password

POST /api/v1/auth/reset-password
```

Protected APIs

```
/api/v1/customers/**

/api/v1/accounts/**
```

---

# Security Components

Implemented

```
SecurityConfig

JwtService

JwtAuthenticationFilter

CustomUserDetailsService
```

Responsibilities

SecurityConfig

- Configure Spring Security
- Configure Filter Chain

JwtService

- Generate JWT
- Validate JWT
- Extract Claims

JwtAuthenticationFilter

- Authenticate every request

CustomUserDetailsService

- Load user from database

---

# Database Changes

Created

```
User Entity

Customer Entity
```

Relationship

```
User

↓

Customer
```

---

# REST APIs Developed

Authentication APIs

```
POST /api/v1/auth/register

POST /api/v1/auth/login
```

---

# Validation Implemented

- Email validation
- Duplicate email check
- Duplicate phone number check
- Mandatory field validation
- Password encryption

---

# Exception Handling

Implemented custom exceptions

- EmailAlreadyExistsException
- PhoneAlreadyExistsException
- UserNotFoundException

Global exception handling using

```
@ControllerAdvice
```

---

# Testing

Successfully tested

✅ User Registration

✅ Duplicate Email Validation

✅ Duplicate Phone Validation

✅ Password Encryption

✅ Login

✅ JWT Generation

✅ Protected APIs

✅ Unauthorized Access

---

# Git Workflow

Completed using feature branch workflow.

Development Steps

```
Create Feature Branch

↓

Develop

↓

Test

↓

Commit

↓

Push

↓

Merge
```

---

# Challenges Faced

- Spring Security configuration
- JWT implementation
- Password encryption
- Authentication filter setup
- Security configuration

All issues were resolved successfully.

---

# Lessons Learned

- Spring Security
- JWT Authentication
- BCrypt Password Encoding
- Stateless Authentication
- Filter Chain
- SecurityContextHolder
- UserDetailsService
- REST API Security

---

# Sprint Outcome

Sprint 1 successfully established a secure authentication and authorization system for the Digital Banking Backend.

The application now supports secure user registration, login, JWT-based authentication, encrypted password storage, and protected REST APIs using Spring Security. This sprint provides the security foundation required for all subsequent banking features.