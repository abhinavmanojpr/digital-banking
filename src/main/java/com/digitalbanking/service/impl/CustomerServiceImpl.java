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

import com.digitalbanking.dto.request.UpdateCustomerProfileRequest;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import com.digitalbanking.exception.PhoneAlreadyExistsException;


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

   @Override
    @Transactional
    public CustomerProfileResponse updateMyProfile(UpdateCustomerProfileRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Customer customer = customerRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        
        Optional<Customer> existingCustomer =customerRepository.findByPhone(request.getPhone());

        if (existingCustomer.isPresent()
            && !existingCustomer.get().getId().equals(customer.getId())) {

            throw new PhoneAlreadyExistsException("Phone number already exists");
        }


        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setPhone(request.getPhone());
        customer.setDateOfBirth(request.getDateOfBirth());
        customer.setAddress(request.getAddress());

        customerRepository.save(customer);

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