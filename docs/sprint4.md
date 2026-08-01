# Sprint 4 – Financial Transactions Module

## Sprint Overview

**Sprint Duration:** Financial Transactions Development

**Sprint Goal:**

Implement secure financial transaction processing, enabling customers to perform deposits, withdrawals, fund transfers, view transaction history, and check account balances while maintaining data consistency through transactional database operations.

---

# Objectives

- Implement deposit functionality.
- Implement withdrawal functionality.
- Implement fund transfer.
- Record every financial transaction.
- Provide transaction history.
- Implement balance enquiry.
- Ensure transactional consistency.
- Enforce banking business rules.

---

# User Stories

As an authenticated customer, I want to:

- Deposit money into my account.
- Withdraw money from my account.
- Transfer money to another account.
- View my transaction history.
- Check my account balance.
- Ensure only I can access my own account information.

---

# Features Implemented

## Deposit Money

Implemented secure deposit functionality.

Features

- Increase account balance.
- Record deposit transaction.
- Return updated balance.
- Validate account ownership.

---

## Withdraw Money

Implemented secure withdrawal functionality.

Features

- Verify sufficient balance.
- Deduct withdrawal amount.
- Record withdrawal transaction.
- Return updated balance.

Business Rule

```
Balance >= Withdrawal Amount
```

Otherwise

```
InsufficientBalanceException
```

is thrown.

---

## Fund Transfer

Implemented secure account-to-account transfers.

Transfer Flow

```
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

Record Sender Transaction

↓

Record Receiver Transaction

↓

Commit Transaction
```

Features

- Validate sender ownership.
- Validate receiver account.
- Prevent self-transfer.
- Verify available balance.
- Record both debit and credit transactions.

---

## Transaction Recording

Every financial operation creates a transaction record.

Supported transaction types

- DEPOSIT
- WITHDRAW
- TRANSFER

Each transaction stores

- Transaction Type
- Amount
- Balance After Transaction
- Account Reference
- Timestamp

---

## Transaction History

Implemented transaction history retrieval.

Features

- View account transactions.
- Order by latest first.
- Display transaction details.
- Restrict access to account owner.

Returned information

- Transaction Type
- Amount
- Balance After Transaction
- Created Timestamp

---

## Balance Enquiry

Implemented balance enquiry API.

Returns

- Account Number
- Current Balance

Only accessible by the account owner.

---

# Database Changes

## New Entity

```
Transaction
```

Relationship

```
Account

↓

One-to-Many

↓

Transaction
```

Every account can contain multiple financial transactions.

---

# Financial Business Rules

Implemented

- Account ownership validation
- Sufficient balance verification
- Prevent self-transfer
- Receiver account validation
- Positive transaction amount validation
- Automatic transaction recording

---

# Transaction Management

Financial operations use

```
@Transactional
```

Purpose

- Atomic operations
- Automatic rollback
- Data consistency
- ACID compliance

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

OR

Rollback
```

---

# Financial Calculations

All monetary operations use

```
BigDecimal
```

Operations used

- add()
- subtract()
- compareTo()

Reason

Avoid floating-point precision errors in financial calculations.

---

# REST APIs Developed

## Deposit

```
POST /api/v1/accounts/{accountNumber}/deposit
```

---

## Withdraw

```
POST /api/v1/accounts/{accountNumber}/withdraw
```

---

## Transfer

```
POST /api/v1/accounts/transfer
```

---

## Transaction History

```
GET /api/v1/accounts/{accountNumber}/transactions
```

---

## Balance Enquiry

```
GET /api/v1/accounts/{accountNumber}/balance
```

---

# Exception Handling

Added

- InsufficientBalanceException
- SameAccountTransferException
- UnauthorizedAccountAccessException
- AccountNotFoundException

Updated

```
GlobalExceptionHandler
```

to support financial transaction exceptions.

---

# Testing

Successfully tested

## Deposit

- Valid deposit
- Invalid account
- Unauthorized access

---

## Withdraw

- Successful withdrawal
- Insufficient balance
- Unauthorized account
- Invalid account

---

## Fund Transfer

- Successful transfer
- Self-transfer prevention
- Invalid receiver
- Invalid sender
- Insufficient balance
- Unauthorized sender

---

## Transaction History

- Retrieve transaction list
- Empty transaction history
- Unauthorized access
- Invalid account

---

## Balance Enquiry

- Retrieve balance
- Invalid account
- Unauthorized access

---

# Git Workflow

Development followed the established workflow.

```
Feature Branch

↓

Develop

↓

Test

↓

Refactor

↓

Commit

↓

Push

↓

Merge
```

Sprint completed with a Git checkpoint before merging into the main branch.

---

# Challenges Faced

- Designing transactional financial operations.
- Maintaining database consistency.
- Preventing unauthorized account access.
- Recording transfer transactions correctly.
- Avoiding floating-point precision issues.
- Implementing reusable service methods.

All challenges were successfully resolved.

---

# Lessons Learned

- Transaction Management
- @Transactional
- BigDecimal
- Financial Business Rules
- Banking Domain Modeling
- Atomic Database Operations
- Service Layer Refactoring
- Authorization
- Audit Trail Design

---

# Deliverables

Completed

- Deposit API
- Withdraw API
- Fund Transfer API
- Transaction Entity
- Transaction Repository
- Transaction History API
- Balance Enquiry API
- Transaction Recording
- Banking Business Rules

---

# Sprint Metrics

| Metric | Value |
|---------|------:|
| Features Delivered | 5 |
| REST APIs Added | 5 |
| New Entity | 1 |
| New Repository | 1 |
| Custom Exceptions Added | 4 |
| Business Rules Implemented | 6 |

---

# Sprint Outcome

Sprint 4 successfully transformed the application into a functional digital banking backend by introducing secure financial transaction processing.

Customers can now perform deposits, withdrawals, fund transfers, view transaction history, and check account balances. Every transaction is recorded for auditing, all financial operations are protected through authorization checks, and transactional consistency is maintained using Spring's `@Transactional` support.

This sprint represents the core banking engine of the application and establishes the foundation for future production-ready enhancements.