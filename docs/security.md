# Security Documentation

## Overview

The Digital Banking Backend uses **Spring Security** and **JWT (JSON Web Token)** to secure REST APIs. The application follows a stateless authentication mechanism where every protected request is authenticated using a JWT.

---

# Security Architecture

```
Client
   │
   │ Login Request
   ▼
Authentication Controller
   │
   ▼
Authentication Service
   │
   ▼
UserDetailsService
   │
   ▼
Database
   │
   ▼
Generate JWT
   │
   ▼
Client Stores JWT
   │
   ▼
Protected API Request
   │
Authorization: Bearer <JWT>
   │
   ▼
JWT Authentication Filter
   │
   ▼
Spring Security
   │
   ▼
Controller
```

---

# Authentication Flow

## Step 1 – User Login

The user sends credentials.

```http
POST /api/v1/auth/login
```

Request

```json
{
    "email":"abhinav@gmail.com",
    "password":"Password@123"
}
```

---

## Step 2 – Credential Validation

Spring Security loads the user using

```
CustomUserDetailsService
```

Password verification is performed using

```
BCryptPasswordEncoder
```

---

## Step 3 – JWT Generation

If authentication succeeds

```
JwtService.generateToken(email)
```

returns

```
JWT Token
```

Example

```
eyJhbGciOiJIUzI1NiJ9...
```

---

## Step 4 – Client Stores JWT

The client stores the JWT securely and sends it with every protected request.

```
Authorization: Bearer <JWT_TOKEN>
```

---

## Step 5 – JWT Authentication Filter

For every protected request

```
JwtAuthenticationFilter
```

performs

- Read Authorization Header
- Extract JWT
- Validate Signature
- Validate Expiration
- Extract Email
- Load UserDetails
- Create Authentication Object
- Store Authentication in SecurityContext

---

## Step 6 – Access Protected APIs

If JWT is valid

```
SecurityContextHolder
```

contains the authenticated user.

The request proceeds to the controller.

Otherwise

```
401 Unauthorized
```

or

```
403 Forbidden
```

is returned.

---

# Spring Security Configuration

The application uses

```
SecurityFilterChain
```

Configuration

- Disable CSRF
- Stateless Session
- JWT Authentication
- Permit Authentication APIs
- Secure Remaining APIs

Authentication APIs

```
/api/v1/auth/**
```

Protected APIs

```
/api/v1/customers/**
/api/v1/accounts/**
```

---

# Password Security

Passwords are never stored in plain text.

Encryption

```
BCryptPasswordEncoder
```

Example

```
Password@123
```

Stored As

```
$2a$10$xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
```

Verification

```
passwordEncoder.matches()
```

---

# JWT Components

Each JWT contains

- Subject (Email)
- Issued Time
- Expiration Time
- Signature

Example Structure

```
Header

Payload

Signature
```

---

# JWT Expiration

Current Configuration

```
1 Hour
```

Expired tokens are rejected by

```
JwtAuthenticationFilter
```

---

# Authorization

The application ensures that users can access only their own resources.

Examples

Customer Profile

```
Only Owner
```

Account Details

```
Only Account Owner
```

Deposit

```
Only Account Owner
```

Withdraw

```
Only Account Owner
```

Transaction History

```
Only Account Owner
```

Balance Enquiry

```
Only Account Owner
```

Fund Transfer

Sender

```
Must Own Account
```

Receiver

```
Can Be Any Valid Account
```

---

# Account Ownership Validation

Sensitive operations use helper methods to verify ownership.

Example

```
getCustomerAccount(accountNumber)
```

This method

- Finds the authenticated user
- Loads the customer
- Loads the account
- Confirms ownership
- Throws UnauthorizedAccountAccessException if validation fails

---

# Security Components

The project contains the following security classes.

```
SecurityConfig

JwtService

JwtAuthenticationFilter

CustomUserDetailsService
```

Responsibilities

SecurityConfig

- Spring Security Configuration

JwtService

- Generate JWT
- Validate JWT
- Extract Claims

JwtAuthenticationFilter

- Authenticate every request

CustomUserDetailsService

- Load users from database

---

# Exception Handling

Security-related exceptions

- Invalid Credentials
- Expired JWT
- Invalid JWT
- Unauthorized Access
- Account Ownership Violation

---

# Password Management

Supported Features

- Change Password
- Forgot Password
- Reset Password

Security Rules

- Verify old password
- Confirm password matching
- Prevent reuse of existing password
- Store encrypted passwords only

---

# Stateless Authentication

The application does not use HTTP sessions.

Each request contains

```
Authorization: Bearer <JWT>
```

Advantages

- Better scalability
- Stateless architecture
- Suitable for REST APIs
- Cloud-friendly

---

# Security Best Practices

Implemented

- BCrypt Password Hashing
- JWT Authentication
- Stateless Sessions
- Bean Validation
- Global Exception Handling
- Resource Ownership Validation
- Password Confirmation
- Financial Authorization Checks

Future Improvements

- Refresh Tokens
- Role-Based Access Control (RBAC)
- Email Verification
- Multi-Factor Authentication (MFA)
- Rate Limiting
- Account Locking
- Audit Logging

---

# Summary

The Digital Banking Backend follows modern REST API security practices using Spring Security and JWT authentication. Sensitive banking operations are protected through authentication, authorization, encrypted password storage, and resource ownership validation to ensure secure access to customer and financial data.