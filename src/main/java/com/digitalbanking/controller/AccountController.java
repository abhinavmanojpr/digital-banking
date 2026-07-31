package com.digitalbanking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.digitalbanking.dto.request.CreateAccountRequest;
import com.digitalbanking.dto.response.CreateAccountResponse;
import com.digitalbanking.service.AccountService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import com.digitalbanking.dto.response.AccountDetailsResponse;
import com.digitalbanking.dto.response.AccountResponse;

import com.digitalbanking.dto.request.DepositRequest;
import com.digitalbanking.dto.response.DepositResponse;

import com.digitalbanking.dto.request.WithdrawRequest;
import com.digitalbanking.dto.response.WithdrawResponse;


@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@Validated
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<CreateAccountResponse> createAccount(
            @Valid @RequestBody CreateAccountRequest request) {

        CreateAccountResponse response =
                accountService.createAccount(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
        }

     @GetMapping
     public ResponseEntity<List<AccountResponse>> getMyAccounts() {

        return ResponseEntity.ok(accountService.getMyAccounts());
        }

        @GetMapping("/{accountNumber}")
        public ResponseEntity<AccountDetailsResponse> getAccount(
                @PathVariable String accountNumber) {

        return ResponseEntity.ok(
            accountService.getAccount(accountNumber));
        }

        @PostMapping("/{accountNumber}/deposit")
        public ResponseEntity<DepositResponse> deposit(
                @PathVariable String accountNumber,
                @Valid @RequestBody DepositRequest request) {

                DepositResponse response =  accountService.deposit(accountNumber, request);

                return ResponseEntity.ok(response);
        }

        @PostMapping("/{accountNumber}/withdraw")
        public ResponseEntity<WithdrawResponse> withdraw(
                @PathVariable String accountNumber,
                @Valid @RequestBody WithdrawRequest request) {

                WithdrawResponse response =accountService.withdraw(accountNumber, request);

                return ResponseEntity.ok(response);
        }
}