package com.digitalbanking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalbanking.entity.Account;
import com.digitalbanking.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccount(Account account);
    List<Transaction> findByAccountOrderByCreatedAtDesc(Account account);
}