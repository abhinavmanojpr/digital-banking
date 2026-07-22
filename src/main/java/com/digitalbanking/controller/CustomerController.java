package com.digitalbanking.controller;

import com.digitalbanking.dto.response.CustomerProfileResponse;
import com.digitalbanking.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/me")
    public ResponseEntity<CustomerProfileResponse> getMyProfile() {

    CustomerProfileResponse response = customerService.getMyProfile();

    return ResponseEntity.ok(response);
}

}