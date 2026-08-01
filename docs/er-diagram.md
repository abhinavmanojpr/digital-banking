# Entity Relationship Diagram (ER Diagram)

## Overview

The Digital Banking Backend uses a relational database designed around four core entities:

- User
- Customer
- Account
- Transaction

The design follows normalization principles and models the relationships commonly found in digital banking systems.

---

# Entity Relationship Diagram

```
                    +----------------+
                    |      User      |
                    +----------------+
                    | PK id          |
                    | email          |
                    | password       |
                    | role           |
                    | enabled        |
                    | created_at     |
                    | updated_at     |
                    +----------------+
                           │
                           │ One-to-One
                           │
                           ▼
                    +----------------+
                    |    Customer    |
                    +----------------+
                    | PK id          |
                    | first_name     |
                    | last_name      |
                    | phone          |
                    | date_of_birth  |
                    | address        |
                    | FK user_id     |
                    +----------------+
                           │
                           │ One-to-Many
                           │
           ┌───────────────┴───────────────┐
           ▼                               ▼
    +----------------+              +----------------+
    |    Account     |              |    Account     |
    +----------------+              +----------------+
    | PK id          |              | PK id          |
    | account_number |              | account_number |
    | account_type   |              | account_type   |
    | balance        |              | balance        |
    | status         |              | status         |
    | FK customer_id |              | FK customer_id |
    | created_at     |              | created_at     |
    | updated_at     |              | updated_at     |
    +----------------+              +----------------+
           │                               │
           │ One-to-Many                   │ One-to-Many
           ▼                               ▼
    +----------------+              +----------------+
    | Transaction    |              | Transaction    |
    +----------------+              +----------------+
    | PK id          |              | PK id          |
    | transactionType|              | transactionType|
    | amount         |              | amount         |
    | balanceAfter   |              | balanceAfter   |
    | FK account_id  |              | FK account_id  |
    | created_at     |              | created_at     |
    +----------------+              +----------------+
```

---

# Entity Descriptions

## User

Purpose

Stores authentication and authorization details.

Responsibilities

- Login
- Password Management
- JWT Authentication
- Role Management

---

## Customer

Purpose

Stores customer personal information.

Responsibilities

- Personal Details
- Contact Information
- Address
- Profile Management

---

## Account

Purpose

Represents a bank account.

Responsibilities

- Balance
- Account Type
- Account Status
- Ownership

---

## Transaction

Purpose

Stores every financial operation performed on an account.

Responsibilities

- Deposit History
- Withdrawal History
- Transfer History
- Audit Trail

---

# Relationships

## User ↔ Customer

Relationship

```
One-to-One
```

Reason

Each customer has exactly one user account for authentication.

JPA Mapping

```java
@OneToOne
```

---

## Customer ↔ Account

Relationship

```
One-to-Many
```

Reason

A customer can own multiple bank accounts.

Examples

- Savings Account
- Current Account
- Salary Account

JPA Mapping

```java
@OneToMany

@ManyToOne
```

---

## Account ↔ Transaction

Relationship

```
One-to-Many
```

Reason

Every account can contain many transactions throughout its lifetime.

Examples

- Deposit
- Withdraw
- Transfer

JPA Mapping

```java
@OneToMany

@ManyToOne
```

---

# Primary Keys

| Entity | Primary Key |
|----------|-------------|
| User | id |
| Customer | id |
| Account | id |
| Transaction | id |

---

# Foreign Keys

| Child Table | Foreign Key | Parent Table |
|--------------|-------------|--------------|
| customer | user_id | user |
| account | customer_id | customer |
| transaction | account_id | account |

---

# Cardinality

| Relationship | Cardinality |
|--------------|-------------|
| User → Customer | 1 : 1 |
| Customer → Account | 1 : N |
| Account → Transaction | 1 : N |

---

# Why This Design?

The database is designed to separate concerns.

Authentication data is stored separately from customer details.

Benefits

- Better Security
- Better Maintainability
- Easier Scalability
- Reduced Data Duplication

---

# Normalization

The schema follows normalization principles.

Benefits

- Eliminate redundancy
- Improve consistency
- Simplify updates
- Better integrity

---

# Banking Workflow

```
User

↓

Customer

↓

Create Account

↓

Deposit

↓

Withdraw

↓

Transfer

↓

Transaction History
```

---

# Current Database Model

```
                User
                  │
                  ▼
             Customer
                  │
         ┌────────┴────────┐
         ▼                 ▼
    Savings          Current Account
         │                 │
    Transactions      Transactions
```

---

# Future Enhancements

The ER diagram can be extended with additional entities such as:

- Beneficiary
- Loan
- Card
- Fixed Deposit
- Audit Log
- Notification
- OTP
- Role
- Permission

without requiring major changes to the existing schema.

---

# Conclusion

The current ER model provides a solid foundation for a digital banking backend. It supports secure authentication, customer management, account management, and financial transaction processing while maintaining proper relational integrity and scalability.