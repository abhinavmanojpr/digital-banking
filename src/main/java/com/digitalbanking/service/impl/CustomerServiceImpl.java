package com.digitalbanking.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digitalbanking.constants.MessageConstants;
import com.digitalbanking.dto.request.UpdateCustomerProfileRequest;
import com.digitalbanking.dto.response.CustomerProfileResponse;
import com.digitalbanking.entity.Customer;
import com.digitalbanking.entity.User;
import com.digitalbanking.exception.PhoneAlreadyExistsException;
import com.digitalbanking.repository.CustomerRepository;
import com.digitalbanking.service.CustomerService;
import com.digitalbanking.util.AuthenticatedUserUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger =
            LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;
    private final AuthenticatedUserUtil authenticatedUserUtil;

    @Override
    public CustomerProfileResponse getMyProfile() {

        Customer customer = authenticatedUserUtil.getAuthenticatedCustomer();
        User user = customer.getUser();

        logger.debug("Fetching profile for customer ID: {}", customer.getId());

        logger.info("Customer profile retrieved successfully for customer ID: {}",
                customer.getId());

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

       Customer customer = authenticatedUserUtil.getAuthenticatedCustomer();
       User user = customer.getUser();

        logger.info("Updating profile for customer ID: {}", customer.getId());

        Optional<Customer> existingCustomer =
                customerRepository.findByPhone(request.getPhone());

        if (existingCustomer.isPresent()
                && !existingCustomer.get().getId().equals(customer.getId())) {

            logger.warn("Phone number {} is already in use",
                    request.getPhone());

            throw new PhoneAlreadyExistsException(
                    MessageConstants.PHONE_ALREADY_EXISTS);
        }

        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setPhone(request.getPhone());
        customer.setDateOfBirth(request.getDateOfBirth());
        customer.setAddress(request.getAddress());

        customerRepository.save(customer);

        logger.info("Customer profile updated successfully for customer ID: {}",
                customer.getId());

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