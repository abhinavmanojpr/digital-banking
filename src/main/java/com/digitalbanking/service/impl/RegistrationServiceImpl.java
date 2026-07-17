package com.digitalbanking.service.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.digitalbanking.enums.Role;
import com.digitalbanking.dto.request.RegisterRequest;
import com.digitalbanking.dto.response.RegisterResponse;
import com.digitalbanking.entity.User;
import com.digitalbanking.entity.Customer;
import com.digitalbanking.repository.CustomerRepository;
import com.digitalbanking.repository.UserRepository;
import com.digitalbanking.service.RegistrationService;
import com.digitalbanking.exception.EmailAlreadyExistsException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public RegisterResponse register(RegisterRequest request) {

        Optional<User> existingUser =
                userRepository.findByEmail(request.getEmail());

        if (existingUser.isPresent()) {
        throw new EmailAlreadyExistsException("Email already exists");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User();

        user.setEmail(request.getEmail());
        user.setPassword(encodedPassword);
        user.setRole(Role.CUSTOMER);
        user.setEnabled(true);
        
        Customer customer = new Customer();

        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setPhone(request.getPhone());
        customer.setDateOfBirth(request.getDateOfBirth());
        customer.setAddress(request.getAddress());

        customer.setUser(user);

        
        userRepository.save(user);
        customerRepository.save(customer);
        
        return RegisterResponse.builder()
                .message("Registration can continue")
                .build();
    }
}