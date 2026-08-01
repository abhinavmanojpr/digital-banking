# API Documentation

## Overview

The Digital Banking Backend exposes RESTful APIs for authentication, customer management, account management, and financial transactions.

Base URL

```
http://localhost:8080/api/v1
```

---

# Authentication

Most APIs require JWT authentication.

Header

```
Authorization: Bearer <JWT_TOKEN>
```

---

# Authentication APIs

## 1. Register User

### Endpoint

```
POST /auth/register
```

### Description

Registers a new customer and user account.

### Request Body

```json
{
    "firstName": "Abhinav",
    "lastName": "Manoj",
    "email": "abhinav@gmail.com",
    "password": "Password@123",
    "phone": "9876543210",
    "dateOfBirth": "2003-10-15",
    "address": "Kannur, Kerala"
}
```

### Success Response

```json
{
    "message": "Registration successful"
}
```

### Status Codes

| Code | Description |
|------|-------------|
|201|Created|
|400|Validation Error|
|409|Email Already Exists|

---

## 2. Login

### Endpoint

```
POST /auth/login
```

### Request

```json
{
    "email":"abhinav@gmail.com",
    "password":"Password@123"
}
```

### Success Response

```json
{
    "token":"<JWT_TOKEN>"
}
```

### Status Codes

| Code | Description |
|------|-------------|
|200|Success|
|401|Invalid Credentials|

---

## 3. Change Password

### Endpoint

```
POST /auth/change-password
```

Authentication Required

### Request

```json
{
    "oldPassword":"Password@123",
    "newPassword":"Password@456",
    "confirmPassword":"Password@456"
}
```

### Success Response

```json
{
    "message":"Password changed successfully"
}
```

### Possible Errors

- Old password incorrect
- Passwords do not match
- New password same as old

---

## 4. Forgot Password

### Endpoint

```
POST /auth/forgot-password
```

### Request

```json
{
    "email":"abhinav@gmail.com"
}
```

### Success Response

```json
{
    "message":"Reset token generated",
    "resetToken":"xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx"
}
```

---

## 5. Reset Password

### Endpoint

```
POST /auth/reset-password
```

### Request

```json
{
    "token":"xxxxxxxx-xxxx",
    "newPassword":"Password@123",
    "confirmPassword":"Password@123"
}
```

### Success Response

```json
{
    "message":"Password reset successfully"
}
```

---

# Customer APIs

Authentication Required

---

## 1. Get Profile

### Endpoint

```
GET /customers/me
```

### Success Response

```json
{
    "id":1,
    "firstName":"Abhinav",
    "lastName":"Manoj",
    "email":"abhinav@gmail.com",
    "phone":"9876543210",
    "dateOfBirth":"2003-10-15",
    "address":"Kannur"
}
```

---

## 2. Update Profile

### Endpoint

```
PUT /customers/me
```

### Request

```json
{
    "firstName":"Abhinav",
    "lastName":"Manoj",
    "phone":"9999999999",
    "dateOfBirth":"2003-10-15",
    "address":"Bangalore"
}
```

### Success Response

Returns updated profile.

---

# Account APIs

Authentication Required

---

## 1. Create Account

### Endpoint

```
POST /accounts
```

### Request

```json
{
    "accountType":"SAVINGS",
    "initialDeposit":5000
}
```

### Success Response

```json
{
    "message":"Account created successfully",
    "accountNumber":"1000000001",
    "accountType":"SAVINGS",
    "balance":5000,
    "status":"ACTIVE"
}
```

---

## 2. Get My Accounts

### Endpoint

```
GET /accounts
```

### Success Response

```json
[
    {
        "accountNumber":"1000000001",
        "accountType":"SAVINGS",
        "balance":5000,
        "status":"ACTIVE"
    }
]
```

---

## 3. Get Account Details

### Endpoint

```
GET /accounts/{accountNumber}
```

Returns account details.

Possible Errors

- Account Not Found
- Unauthorized Access

---

## 4. Balance Enquiry

### Endpoint

```
GET /accounts/{accountNumber}/balance
```

### Success Response

```json
{
    "accountNumber":"1000000001",
    "balance":8500
}
```

---

# Transaction APIs

Authentication Required

---

## 1. Deposit

### Endpoint

```
POST /accounts/{accountNumber}/deposit
```

### Request

```json
{
    "amount":5000
}
```

### Success Response

```json
{
    "message":"Amount deposited successfully",
    "accountNumber":"1000000001",
    "previousBalance":5000,
    "depositedAmount":5000,
    "currentBalance":10000
}
```

---

## 2. Withdraw

### Endpoint

```
POST /accounts/{accountNumber}/withdraw
```

### Request

```json
{
    "amount":1000
}
```

### Success Response

```json
{
    "message":"Amount withdrawn successfully",
    "accountNumber":"1000000001",
    "previousBalance":10000,
    "withdrawnAmount":1000,
    "currentBalance":9000
}
```

Possible Errors

- Insufficient Balance
- Unauthorized Access
- Account Not Found

---

## 3. Fund Transfer

### Endpoint

```
POST /accounts/transfer
```

### Request

```json
{
    "fromAccountNumber":"1000000001",
    "toAccountNumber":"1000000002",
    "amount":3000
}
```

### Success Response

```json
{
    "message":"Amount transferred successfully",
    "fromAccountNumber":"1000000001",
    "toAccountNumber":"1000000002",
    "transferredAmount":3000,
    "senderBalance":7000,
    "receiverBalance":8000
}
```

Possible Errors

- Same Account Transfer
- Insufficient Balance
- Receiver Not Found
- Unauthorized Sender

---

## 4. Transaction History

### Endpoint

```
GET /accounts/{accountNumber}/transactions
```

### Success Response

```json
[
    {
        "transactionType":"TRANSFER",
        "amount":3000,
        "balanceAfterTransaction":7000,
        "createdAt":"2026-07-31T20:15:30"
    },
    {
        "transactionType":"DEPOSIT",
        "amount":5000,
        "balanceAfterTransaction":10000,
        "createdAt":"2026-07-31T19:00:00"
    }
]
```

---

# Authentication Summary

| API | JWT Required |
|------|--------------|
|Register|❌|
|Login|❌|
|Forgot Password|❌|
|Reset Password|❌|
|Change Password|✅|
|Get Profile|✅|
|Update Profile|✅|
|Create Account|✅|
|Get My Accounts|✅|
|Get Account Details|✅|
|Balance Enquiry|✅|
|Deposit|✅|
|Withdraw|✅|
|Transfer|✅|
|Transaction History|✅|

---

# HTTP Status Codes

| Status Code | Meaning |
|------------|---------|
|200|Success|
|201|Resource Created|
|400|Bad Request|
|401|Unauthorized|
|403|Forbidden|
|404|Resource Not Found|
|409|Conflict|

---

# API Statistics

| Category | Count |
|----------|-------:|
|Authentication APIs|5|
|Customer APIs|2|
|Account APIs|4|
|Transaction APIs|4|

**Total REST APIs:** **15**