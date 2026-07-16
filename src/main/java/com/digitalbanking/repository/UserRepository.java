package com.digitalbanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalbanking.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}