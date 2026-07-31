package com.digitalbanking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalbanking.entity.Account;
import com.digitalbanking.entity.Customer;

import java.util.List;



public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNumber(String accountNumber);

    Optional<Account> findTopByOrderByIdDesc();

    List<Account> findByCustomer(Customer customer);
}