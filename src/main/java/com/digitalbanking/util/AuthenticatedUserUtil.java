package com.digitalbanking.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.digitalbanking.constants.MessageConstants;
import com.digitalbanking.entity.Customer;
import com.digitalbanking.entity.User;
import com.digitalbanking.exception.CustomerNotFoundException;
import com.digitalbanking.exception.UserNotFoundException;
import com.digitalbanking.repository.CustomerRepository;
import com.digitalbanking.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthenticatedUserUtil {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;

    /**
     * Returns the email of the currently authenticated user.
     */
    public String getAuthenticatedEmail() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }

    /**
     * Returns the authenticated user.
     */
    public User getAuthenticatedUser() {

        return userRepository.findByEmail(getAuthenticatedEmail())
                .orElseThrow(() ->
                        new UserNotFoundException(
                                MessageConstants.USER_NOT_FOUND));
    }

    /**
     * Returns the authenticated customer.
     */
    public Customer getAuthenticatedCustomer() {

        return customerRepository.findByUser(getAuthenticatedUser())
                .orElseThrow(() ->
                        new CustomerNotFoundException(
                                MessageConstants.CUSTOMER_NOT_FOUND));
    }
}