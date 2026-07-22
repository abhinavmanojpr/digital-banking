package com.digitalbanking.service.impl;

import com.digitalbanking.dto.response.CustomerProfileResponse;
import com.digitalbanking.repository.CustomerRepository;
import com.digitalbanking.repository.UserRepository;
import com.digitalbanking.service.CustomerService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import com.digitalbanking.entity.Customer;
import com.digitalbanking.entity.User;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;

    @Override
    public CustomerProfileResponse getMyProfile() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found"));
        Customer customer = customerRepository.findByUser(user)
        .orElseThrow(() -> new RuntimeException("Customer not found"));

        return CustomerProfileResponse.builder()
        .id(customer.getId())
        .firstName(customer.getFirstName())
        .lastName(customer.getLastName())
        .email(user.getEmail())
        .phone(customer.getPhone())
        .dateOfBirth(customer.getDateOfBirth())
        .address(customer.getAddress())
        .build();
    }
}