# Database Schema

## Overview

The Digital Banking Backend uses **PostgreSQL** as its relational database management system.

The database is designed using normalization principles and follows a relational model with proper primary keys, foreign keys, constraints, and entity relationships.

---

# Database Tables

The application currently contains the following tables:

- user
- customer
- account
- transaction

---

# Entity Relationship Diagram

```
User
 │
 │ One-to-One
 ▼
Customer
 │
 │ One-to-Many
 ▼
Account
 │
 │ One-to-Many
 ▼
Transaction
```

---

# Table: user in sql (app_user)

Purpose

Stores authentication and authorization information.

| Column | Data Type | Constraints | Description |
|---------|-----------|------------|-------------|
| id | BIGINT | Primary Key | Unique identifier |
| email | VARCHAR | Unique, Not Null | User email |
| password | VARCHAR | Not Null | Encrypted password |
| role | VARCHAR | Not Null | User role |
| enabled | BOOLEAN | Not Null | Account status |
| created_at | TIMESTAMP | Not Null | Creation timestamp |
| updated_at | TIMESTAMP | Not Null | Last update timestamp |

Primary Key

```
id
```

Unique Constraint

```
email
```

Relationship

```
User (1)

↓

Customer (1)
```

---

# Table: customer

Purpose

Stores customer personal information.

| Column | Data Type | Constraints | Description |
|---------|-----------|------------|-------------|
| id | BIGINT | Primary Key | Customer ID |
| first_name | VARCHAR | Not Null | First Name |
| last_name | VARCHAR | Not Null | Last Name |
| phone | VARCHAR | Unique | Mobile Number |
| date_of_birth | DATE | Not Null | Date of Birth |
| address | VARCHAR | Not Null | Address |
| user_id | BIGINT | Foreign Key | Reference to User |

Primary Key

```
id
```

Foreign Key

```
user_id
```

Relationship

```
Customer

↓

User
```

Cardinality

```
One Customer

↓

One User
```

---

# Table: account

Purpose

Stores customer bank accounts.

| Column | Data Type | Constraints | Description |
|---------|-----------|------------|-------------|
| id | BIGINT | Primary Key | Account ID |
| account_number | VARCHAR | Unique | Bank Account Number |
| account_type | VARCHAR | Not Null | SAVINGS / CURRENT |
| balance | DECIMAL(19,2) | Not Null | Current Balance |
| status | VARCHAR | Not Null | ACTIVE / INACTIVE |
| customer_id | BIGINT | Foreign Key | Owner of account |
| created_at | TIMESTAMP | Not Null | Creation timestamp |
| updated_at | TIMESTAMP | Not Null | Last update timestamp |

Primary Key

```
id
```

Unique Constraint

```
account_number
```

Foreign Key

```
customer_id
```

Relationship

```
Customer

↓

Many Accounts
```

---

# Table: transaction

Purpose

Stores all financial transactions.

| Column | Data Type | Constraints | Description |
|---------|-----------|------------|-------------|
| id | BIGINT | Primary Key | Transaction ID |
| transaction_type | VARCHAR | Not Null | DEPOSIT / WITHDRAW / TRANSFER |
| amount | DECIMAL(19,2) | Not Null | Transaction Amount |
| balance_after_transaction | DECIMAL(19,2) | Not Null | Balance after transaction |
| account_id | BIGINT | Foreign Key | Account Reference |
| created_at | TIMESTAMP | Not Null | Transaction Time |

Primary Key

```
id
```

Foreign Key

```
account_id
```

Relationship

```
Account

↓

Many Transactions
```

---

# Entity Relationships

## User ↔ Customer

Relationship

```
One-to-One
```

Reason

Each customer has exactly one login account.

---

## Customer ↔ Account

Relationship

```
One-to-Many
```

Reason

One customer can own multiple bank accounts.

Example

```
Savings Account

Current Account

Salary Account
```

---

## Account ↔ Transaction

Relationship

```
One-to-Many
```

Reason

Every account can have unlimited financial transactions.

Examples

- Deposit
- Withdraw
- Transfer

---

# Database Constraints

## Primary Keys

- user.id
- customer.id
- account.id
- transaction.id

---

## Foreign Keys

- customer.user_id
- account.customer_id
- transaction.account_id

---

## Unique Constraints

- user.email
- customer.phone
- account.account_number

---

## NOT NULL Constraints

Applied to all mandatory business fields.

Examples

- email
- password
- balance
- transaction_type
- amount

---

# Data Types

| Type | Purpose |
|------|----------|
| BIGINT | IDs |
| VARCHAR | Text |
| BOOLEAN | True / False |
| DATE | Date of Birth |
| TIMESTAMP | Audit Columns |
| DECIMAL(19,2) | Monetary Values |

---

# Why BigDecimal?

Financial calculations require high precision.

```
double

↓

Precision Errors
```

Instead

```
BigDecimal

↓

Accurate Monetary Calculations
```

Used for

- Account Balance
- Deposit
- Withdraw
- Transfer

---

# Normalization

The database follows normalization principles.

Benefits

- Reduced redundancy
- Better consistency
- Easier maintenance
- Improved scalability

---

# Audit Columns

The following tables maintain timestamps.

- user
- account
- transaction

Used for

- Auditing
- Reporting
- Transaction History

---

# Current Database Design

```
                   User
                     │
                     │
                     ▼
                 Customer
                     │
          ┌──────────┴──────────┐
          ▼                     ▼
     Savings Account      Current Account
          │                     │
      Transactions         Transactions
```

---

# Summary

The database schema is designed using relational database principles and supports secure authentication, customer management, account management, and financial transaction processing. The design emphasizes normalization, data integrity, and scalability while maintaining clear relationships between entities.