package com.digitalbanking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbanking.dto.request.RegisterRequest;
import com.digitalbanking.dto.response.RegisterResponse;
import com.digitalbanking.service.RegistrationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

   @PostMapping("/register")
public ResponseEntity<RegisterResponse> register(
        @Valid
        @RequestBody RegisterRequest request) {

    RegisterResponse response = registrationService.register(request);

    return ResponseEntity.status(HttpStatus.CREATED)
            .body(response);
}
}