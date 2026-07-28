package com.digitalbanking.controller;

import com.digitalbanking.dto.response.CustomerProfileResponse;
import com.digitalbanking.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.digitalbanking.dto.request.UpdateCustomerProfileRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    @GetMapping("/hello")
    public String hello() {
        return "Hello Customer";
    }

    @GetMapping("/me")
    public ResponseEntity<CustomerProfileResponse> getMyProfile() {

    CustomerProfileResponse response = customerService.getMyProfile();

    return ResponseEntity.ok(response);
    }

    @PutMapping("/me")
    public ResponseEntity<CustomerProfileResponse> updateMyProfile(
            @Valid @RequestBody UpdateCustomerProfileRequest request) {

        CustomerProfileResponse response =customerService.updateMyProfile(request);

        return ResponseEntity.ok(response);
    }

}