# Sprint 3 – Account Management Module

## Sprint Overview

**Sprint Duration:** Account Management Development

**Sprint Goal:**

Develop the account management module to enable customers to create and manage bank accounts while ensuring secure account ownership validation.

---

# Objectives

- Implement account creation.
- Generate unique account numbers.
- View all customer accounts.
- View account details.
- Enforce account ownership validation.
- Refactor business logic for maintainability.

---

# User Stories

As an authenticated customer, I want to:

- Create a bank account.
- View all my bank accounts.
- View the details of a specific account.
- Prevent other users from accessing my accounts.

---

# Features Implemented

## Create Account

Implemented functionality to create new bank accounts.

Supported account types

- SAVINGS
- CURRENT

During account creation

- A unique account number is generated.
- Initial deposit is stored.
- Account status is set to ACTIVE.
- Account is linked to the authenticated customer.

---

## Account Number Generation

Implemented automatic account number generation.

Logic

```
Find Latest Account

↓

Increment Account Number

↓

Assign New Account Number
```

Example

```
1000000001

↓

1000000002

↓

1000000003
```

---

## View My Accounts

Implemented API to retrieve all accounts belonging to the authenticated customer.

Returned information

- Account Number
- Account Type
- Balance
- Account Status

---

## Account Details

Implemented API to retrieve details of a specific account.

Returned information

- Account Number
- Account Type
- Balance
- Account Status

---

# Authorization

Implemented ownership validation.

Only the authenticated account owner can

- View Account Details
- Perform future account operations

Unauthorized access returns

```
403 Forbidden
```

---

# Refactoring

To eliminate duplicate code, common account validation logic was extracted into helper methods.

Example

```
getAuthenticatedCustomer()

↓

getCustomerAccount(accountNumber)
```

Benefits

- Cleaner services
- Better readability
- Reduced code duplication
- Easier maintenance

---

# Database Changes

## New Entity

```
Account
```

Relationship

```
Customer

↓

One-to-Many

↓

Account
```

Each customer can own multiple bank accounts.

---

# REST APIs Developed

## Account APIs

```
POST /api/v1/accounts

GET /api/v1/accounts

GET /api/v1/accounts/{accountNumber}
```

---

# Validation

Implemented

- Authenticated customer validation
- Account ownership validation
- Unique account number generation
- Initial deposit validation

---

# Exception Handling

Added custom exceptions

- AccountNotFoundException
- UnauthorizedAccountAccessException

Updated

```
GlobalExceptionHandler
```

to return standardized error responses.

---

# Testing

Successfully tested

✅ Create Savings Account

✅ Create Current Account

✅ Generate Unique Account Number

✅ View My Accounts

✅ View Account Details

✅ Invalid Account Number

✅ Unauthorized Account Access

---

# Git Workflow

Development followed the project Git workflow.

```
Feature Branch

↓

Implementation

↓

Testing

↓

Refactoring

↓

Commit

↓

Push

↓

Merge
```

---

# Challenges Faced

- Designing account relationships.
- Generating sequential account numbers.
- Implementing ownership validation.
- Refactoring duplicated service logic.

All challenges were resolved successfully.

---

# Lessons Learned

- One-to-Many Relationships
- Entity Associations
- Repository Query Methods
- Business Logic Refactoring
- Authorization
- Clean Service Design

---

# Sprint Outcome

Sprint 3 successfully introduced the account management module.

Customers can now create bank accounts, retrieve account information, and securely access only their own accounts. The project architecture was further improved through service refactoring and reusable helper methods, laying the foundation for financial transaction processing in Sprint 4.