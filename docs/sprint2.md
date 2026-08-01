# Sprint 2 – Customer Management Module

## Sprint Overview

**Sprint Duration:** Customer Management Development

**Sprint Goal:**

Develop the customer management module, allowing authenticated users to manage their profile information and securely maintain their account credentials through password management and recovery features.

---

# Objectives

- Implement customer profile retrieval.
- Implement customer profile update.
- Implement password change.
- Implement forgot password.
- Implement password reset.
- Improve exception handling.
- Strengthen API validation.

---

# User Stories

As an authenticated customer, I want to:

- View my profile.
- Update my personal information.
- Change my password securely.
- Reset my password if I forget it.
- Receive meaningful error messages when validation fails.

---

# Features Implemented

## Customer Profile

Implemented APIs to

- View authenticated customer profile.
- Update customer profile.

Users can update

- First Name
- Last Name
- Phone Number
- Date of Birth
- Address

---

## Change Password

Implemented secure password change functionality.

Validation includes

- Verify old password
- Confirm new password
- Prevent reuse of old password

Passwords continue to be stored using BCrypt encryption.

---

## Forgot Password

Implemented password recovery workflow.

Flow

```
Enter Email

↓

Generate Reset Token

↓

Return Reset Token

↓

Use Token to Reset Password
```

---

## Reset Password

Implemented password reset functionality.

Validation includes

- Valid reset token
- Matching password confirmation
- Password update after successful validation

---

# Security Improvements

Enhanced authentication by adding

- Password change
- Password recovery
- Password reset

Password operations are available only to authenticated users, except for forgot/reset password, which use a reset token.

---

# Validation Implemented

Implemented request validation using Jakarta Bean Validation.

Examples

- @NotBlank
- @NotNull
- @Email
- @Valid

Business validations

- Old password verification
- Matching passwords
- Prevent duplicate password
- Valid reset token

---

# Exception Handling

Added custom exceptions

- OldPasswordIncorrectException
- PasswordsDoNotMatchException
- SamePasswordException

Updated

```
GlobalExceptionHandler
```

to return consistent API responses.

---

# REST APIs Developed

Customer APIs

```
GET /api/v1/customers/me

PUT /api/v1/customers/me
```

Authentication APIs

```
POST /api/v1/auth/change-password

POST /api/v1/auth/forgot-password

POST /api/v1/auth/reset-password
```

---

# Database Changes

No new tables were introduced.

Existing tables

- user
- customer

were updated through business operations.

---

# Testing

Successfully tested

✅ Get Customer Profile

✅ Update Customer Profile

✅ Change Password

✅ Forgot Password

✅ Reset Password

✅ Invalid Old Password

✅ Password Confirmation Validation

✅ Duplicate Password Validation

✅ Invalid Reset Token

---

# Git Workflow

Development followed the standard workflow.

```
Feature Branch

↓

Implementation

↓

Testing

↓

Commit

↓

Push

↓

Merge
```

---

# Challenges Faced

- Secure password validation
- Password recovery workflow
- Token validation
- Exception handling consistency
- Business rule validation

All challenges were resolved successfully.

---

# Lessons Learned

- SecurityContextHolder
- PasswordEncoder
- Business Validation
- Custom Exceptions
- DTO Validation
- Password Recovery Workflow
- Secure REST API Design

---

# Sprint Outcome

Sprint 2 successfully expanded the application beyond authentication by introducing customer profile management and secure password management features.

The backend now supports profile maintenance, password updates, password recovery, and consistent validation while maintaining secure authentication and authorization practices.