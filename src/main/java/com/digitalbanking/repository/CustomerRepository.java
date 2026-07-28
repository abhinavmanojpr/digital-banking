package com.digitalbanking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalbanking.entity.Customer;
import com.digitalbanking.entity.User;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByUser(User user);

    Optional<Customer> findByPhone(String phone);

}