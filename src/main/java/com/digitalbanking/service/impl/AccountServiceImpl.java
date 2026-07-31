package com.digitalbanking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.digitalbanking.dto.request.CreateAccountRequest;
import com.digitalbanking.dto.response.AccountDetailsResponse;
import com.digitalbanking.dto.response.AccountResponse;
import com.digitalbanking.dto.response.CreateAccountResponse;
import com.digitalbanking.entity.Account;
import com.digitalbanking.entity.Customer;
import com.digitalbanking.entity.User;
import com.digitalbanking.enums.AccountStatus;
import com.digitalbanking.exception.AccountNotFoundException;
import com.digitalbanking.exception.CustomerNotFoundException;
import com.digitalbanking.exception.UnauthorizedAccountAccessException;
import com.digitalbanking.exception.UserNotFoundException;
import com.digitalbanking.repository.AccountRepository;
import com.digitalbanking.repository.CustomerRepository;
import com.digitalbanking.repository.UserRepository;
import com.digitalbanking.service.AccountService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest request) {

        Customer customer = getAuthenticatedCustomer();

        String accountNumber = generateAccountNumber();

        Account account = Account.builder()
                .accountNumber(accountNumber)
                .accountType(request.getAccountType())
                .balance(request.getInitialDeposit())
                .status(AccountStatus.ACTIVE)
                .customer(customer)
                .build();

        accountRepository.save(account);

        return CreateAccountResponse.builder()
                .message("Account created successfully")
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .balance(account.getBalance())
                .status(account.getStatus())
                .build();
    }

    @Override
    public List<AccountResponse> getMyAccounts() {

        Customer customer = getAuthenticatedCustomer();

        List<Account> accounts = accountRepository.findByCustomer(customer);

        return accounts.stream()
                .map(account -> AccountResponse.builder()
                        .accountNumber(account.getAccountNumber())
                        .accountType(account.getAccountType())
                        .balance(account.getBalance())
                        .status(account.getStatus())
                        .build())
                .toList();
    }

    @Override
    public AccountDetailsResponse getAccount(String accountNumber) {

        Account account = getCustomerAccount(accountNumber);

        return AccountDetailsResponse.builder()
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .balance(account.getBalance())
                .status(account.getStatus())
                .build();
    }

    /**
     * Returns the currently authenticated customer.
     */
    private Customer getAuthenticatedCustomer() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        return customerRepository.findByUser(user)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer not found"));
    }

    /**
     * Returns the account if it belongs to the authenticated customer.
     */
    private Account getCustomerAccount(String accountNumber) {

        Customer customer = getAuthenticatedCustomer();

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new AccountNotFoundException("Account not found"));

        if (!account.getCustomer().getId().equals(customer.getId())) {
            throw new UnauthorizedAccountAccessException(
                    "You are not authorized to access this account");
        }

        return account;
    }

    /**
     * Generates the next sequential account number.
     */
    private String generateAccountNumber() {

        Optional<Account> latestAccount =
                accountRepository.findTopByOrderByIdDesc();

        if (latestAccount.isEmpty()) {
            return "1000000001";
        }

        long nextAccountNumber =
                Long.parseLong(latestAccount.get().getAccountNumber()) + 1;

        return String.valueOf(nextAccountNumber);
    }
}