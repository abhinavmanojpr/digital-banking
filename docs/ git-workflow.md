# Git Workflow

## Overview

The Digital Banking Backend project follows a **feature branch workflow**. Every major feature is developed in its own Git branch, thoroughly tested, committed, pushed to GitHub, and finally merged into the `main` branch.

This workflow ensures code stability, better version control, and easier collaboration.

---

# Branch Strategy

The project uses two types of branches.

```
main

feature/<feature-name>
```

Examples

```
feature/user-registration

feature/change-password

feature/account-management

feature/account-transactions

feature/banking-services
```

---

# Development Workflow

```
main

↓

Create Feature Branch

↓

Develop Feature

↓

Test Feature

↓

Commit Changes

↓

Push Feature Branch

↓

Merge into main

↓

Create Next Feature Branch
```

---

# Sprint Workflow

Each sprint follows the same lifecycle.

```
Plan Sprint

↓

Implement Feature

↓

Test APIs

↓

Fix Issues

↓

Refactor Code

↓

Git Checkpoint

↓

Push to GitHub

↓

Merge to main

↓

Start Next Sprint
```

---

# Standard Git Commands

## Clone Repository

```bash
git clone <repository-url>
```

---

## Create Feature Branch

```bash
git checkout -b feature/<feature-name>
```

Example

```bash
git checkout -b feature/account-transactions
```

---

## Check Current Branch

```bash
git branch
```

---

## Check Status

```bash
git status
```

---

## Stage Changes

```bash
git add .
```

---

## Commit Changes

```bash
git commit -m "Meaningful commit message"
```

Examples

```bash
git commit -m "Implement deposit functionality"

git commit -m "Add transaction history API"

git commit -m "Complete Sprint 4 - Financial Transactions Module"
```

---

## Push Feature Branch

```bash
git push origin feature/<feature-name>
```

Example

```bash
git push origin feature/account-transactions
```

---

## Switch to Main

```bash
git checkout main
```

---

## Pull Latest Changes

```bash
git pull origin main
```

---

## Merge Feature Branch

```bash
git merge feature/<feature-name>
```

---

## Push Main

```bash
git push origin main
```

---

# Feature Development Lifecycle

Example

```
Create Account Feature

↓

Create DTO

↓

Create Entity

↓

Repository

↓

Service

↓

Controller

↓

Testing

↓

Refactoring

↓

Git Commit

↓

Git Push

↓

Merge
```

This process is followed for every major feature.

---

# Git Checkpoints

A checkpoint is created after completing every major milestone.

Examples

- Sprint Completion
- Module Completion
- Major Feature Completion

Workflow

```
git status

↓

git add .

↓

git commit

↓

git push
```

Benefits

- Safe rollback point
- Better project history
- Easier debugging

---

# Branch Naming Convention

```
feature/<feature-name>
```

Examples

```
feature/user-registration

feature/change-password

feature/account-management

feature/account-transactions

feature/banking-services
```

Future

```
feature/admin-module

feature/swagger

feature/testing

feature/docker

feature/deployment
```

---

# Commit Message Guidelines

Commit messages should clearly describe the work completed.

Examples

```
Implement JWT authentication

Add customer profile update API

Implement account creation

Implement deposit functionality

Implement fund transfer

Complete Sprint 4 - Financial Transactions Module
```

Avoid

```
Update

Changes

Fix

Code
```

Good commit messages improve project history and readability.

---

# Merge Strategy

The project follows a simple merge strategy.

```
Feature Branch

↓

Fully Tested

↓

Merge into main

↓

Push main
```

Only stable and tested code is merged into `main`.

---

# Best Practices Followed

- One feature per branch.
- Test before committing.
- Commit only working code.
- Push after completing a logical unit of work.
- Merge only after successful testing.
- Keep `main` stable.
- Use meaningful commit messages.

---

# GitHub Repository Structure

```
main

├── Stable Code

├── Documentation

└── Sprint Releases

feature/*

├── New Features

├── Testing

└── Development
```

---

# Benefits of This Workflow

- Clean Git history.
- Easier debugging.
- Safer development.
- Better collaboration.
- Reduced merge conflicts.
- Stable production branch.
- Professional development process.

---

# Workflow Summary

```
Create Feature Branch

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

↓

Next Sprint
```

---

# Conclusion

The Digital Banking Backend project follows a disciplined Git feature branch workflow similar to those used in professional software development teams. Every feature is developed independently, tested thoroughly, committed with meaningful messages, pushed to GitHub, and merged into the main branch only after successful validation. This approach ensures maintainable version history, stable releases, and a structured development process.