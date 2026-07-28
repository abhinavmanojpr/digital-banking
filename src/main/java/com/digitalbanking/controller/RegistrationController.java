package com.digitalbanking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbanking.dto.request.LoginRequest;
import com.digitalbanking.dto.request.RegisterRequest;
import com.digitalbanking.dto.request.ResetPasswordRequest;
import com.digitalbanking.dto.response.LoginResponse;
import com.digitalbanking.dto.response.RegisterResponse;
import com.digitalbanking.dto.response.ResetPasswordResponse;
import com.digitalbanking.service.AuthenticationService;
import com.digitalbanking.service.RegistrationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import com.digitalbanking.dto.request.ChangePasswordRequest;
import com.digitalbanking.dto.response.ChangePasswordResponse;

import org.springframework.web.bind.annotation.PutMapping;


import com.digitalbanking.dto.request.ForgotPasswordRequest;
import com.digitalbanking.dto.response.ForgotPasswordResponse;



@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private final AuthenticationService authenticationService;
    

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        RegisterResponse response = registrationService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response = authenticationService.login(request);

        return ResponseEntity
                .ok(response);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ForgotPasswordResponse> forgotPassword(
        @Valid @RequestBody ForgotPasswordRequest request) {

        ForgotPasswordResponse response = authenticationService.forgotPassword(request);

        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/change-password")
    public ResponseEntity<ChangePasswordResponse> changePassword(
            @Valid @RequestBody ChangePasswordRequest request) {

        return ResponseEntity.ok(authenticationService.changePassword(request));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ResetPasswordResponse> resetPassword(
            @Valid @RequestBody ResetPasswordRequest request) {

        ResetPasswordResponse response =
                authenticationService.resetPassword(request);

        return ResponseEntity.ok(response);
    }
    
}