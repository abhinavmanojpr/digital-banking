package com.digitalbanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalbanking.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}