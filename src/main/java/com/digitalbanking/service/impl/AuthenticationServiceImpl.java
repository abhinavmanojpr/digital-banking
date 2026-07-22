package com.digitalbanking.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.digitalbanking.dto.request.LoginRequest;
import com.digitalbanking.dto.response.LoginResponse;
import com.digitalbanking.entity.User;
import com.digitalbanking.exception.InvalidCredentialsException;
import com.digitalbanking.repository.UserRepository;
import com.digitalbanking.service.AuthenticationService;
import com.digitalbanking.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException(
                                "Invalid email or password"
                        )
                );

        boolean passwordMatches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!passwordMatches) {
            throw new InvalidCredentialsException(
                    "Invalid email or password"
            );
        }

        if (!user.getEnabled()) {
        throw new InvalidCredentialsException("Account is disabled");
        }

         // Generate JWT token
        String accessToken = jwtService.generateToken(user.getEmail());

        return LoginResponse.builder()
                .message("Login successful")
                .accessToken(accessToken)
                .build();
    }
}