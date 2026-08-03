package com.digitalbanking.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digitalbanking.dto.request.CreateAccountRequest;
import com.digitalbanking.dto.request.DepositRequest;
import com.digitalbanking.dto.request.WithdrawRequest;
import com.digitalbanking.dto.response.AccountDetailsResponse;
import com.digitalbanking.dto.response.AccountResponse;
import com.digitalbanking.dto.response.BalanceResponse;
import com.digitalbanking.dto.response.CreateAccountResponse;
import com.digitalbanking.dto.response.DepositResponse;
import com.digitalbanking.dto.response.TransactionResponse;
import com.digitalbanking.dto.response.WithdrawResponse;
import com.digitalbanking.entity.Account;
import com.digitalbanking.entity.Customer;
import com.digitalbanking.enums.AccountStatus;
import com.digitalbanking.entity.Transaction;
import com.digitalbanking.enums.TransactionType;
import com.digitalbanking.exception.AccountNotFoundException;

import com.digitalbanking.exception.InsufficientBalanceException;
import com.digitalbanking.exception.UnauthorizedAccountAccessException;
import com.digitalbanking.repository.AccountRepository;

import com.digitalbanking.repository.TransactionRepository;

import com.digitalbanking.service.AccountService;
import com.digitalbanking.util.AuthenticatedUserUtil;
import com.digitalbanking.dto.request.TransferRequest;
import com.digitalbanking.dto.response.TransferResponse;
import com.digitalbanking.exception.SameAccountTransferException;
import com.digitalbanking.constants.AccountConstants;
import com.digitalbanking.constants.MessageConstants;


import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {


    private static final Logger logger =LoggerFactory.getLogger(AccountServiceImpl.class);    
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final AuthenticatedUserUtil authenticatedUserUtil;
    

    @Override
public CreateAccountResponse createAccount(CreateAccountRequest request) {

   Customer customer = authenticatedUserUtil.getAuthenticatedCustomer();

    logger.info("Creating {} account for customer ID: {}",
            request.getAccountType(),
            customer.getId());

    String accountNumber = generateAccountNumber();

    Account account = Account.builder()
            .accountNumber(accountNumber)
            .accountType(request.getAccountType())
            .balance(request.getInitialDeposit())
            .status(AccountStatus.ACTIVE)
            .customer(customer)
            .build();

    accountRepository.save(account);

    logger.info("Account {} created successfully with initial balance {}",
            account.getAccountNumber(),
            account.getBalance());

    return CreateAccountResponse.builder()
            .message(MessageConstants.ACCOUNT_CREATED)
            .accountNumber(account.getAccountNumber())
            .accountType(account.getAccountType())
            .balance(account.getBalance())
            .status(account.getStatus())
            .build();
}

@Override
public List<AccountResponse> getMyAccounts() {

    Customer customer =authenticatedUserUtil.getAuthenticatedCustomer();

    logger.debug("Fetching accounts for customer ID: {}",
            customer.getId());

    List<Account> accounts = accountRepository.findByCustomer(customer);

    logger.info("Retrieved {} account(s) for customer ID: {}",
            accounts.size(),
            customer.getId());

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

    logger.debug("Fetching account details for account: {}",
            accountNumber);

    Account account = getCustomerAccount(accountNumber);

    logger.info("Account details retrieved for account {}",
            accountNumber);

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

/**
 * Returns the account if it belongs to the authenticated customer.
 */
private Account getCustomerAccount(String accountNumber) {

   Customer customer =authenticatedUserUtil.getAuthenticatedCustomer();

    logger.debug("Validating ownership of account {} for customer ID {}",
            accountNumber,
            customer.getId());

    Account account = accountRepository.findByAccountNumber(accountNumber)
            .orElseThrow(() -> {
                logger.error("Account not found: {}", accountNumber);
                 return new AccountNotFoundException(MessageConstants.ACCOUNT_NOT_FOUND);
            });

    if (!account.getCustomer().getId().equals(customer.getId())) {

        logger.warn("Unauthorized access attempt on account {} by customer ID {}",
                accountNumber,
                customer.getId());

       throw new UnauthorizedAccountAccessException( MessageConstants.UNAUTHORIZED_ACCOUNT_ACCESS);
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

    logger.info("No existing accounts found. Starting account numbering from {}",
            AccountConstants.INITIAL_ACCOUNT_NUMBER);

    return String.valueOf(AccountConstants.INITIAL_ACCOUNT_NUMBER);
}

    long nextAccountNumber =
            Long.parseLong(latestAccount.get().getAccountNumber()) + 1;

    logger.debug("Generated next account number: {}", nextAccountNumber);

    return String.valueOf(nextAccountNumber);
}
       
@Override
@Transactional
public DepositResponse deposit(String accountNumber,
                               DepositRequest request) {

    logger.info("Deposit initiated for account {} with amount {}",
            accountNumber,
            request.getAmount());

    Account account = getCustomerAccount(accountNumber);

    BigDecimal previousBalance = account.getBalance();

    BigDecimal currentBalance =
            previousBalance.add(request.getAmount());

    account.setBalance(currentBalance);

    accountRepository.save(account);

    Transaction transaction = Transaction.builder()
            .transactionType(TransactionType.DEPOSIT)
            .amount(request.getAmount())
            .balanceAfterTransaction(currentBalance)
            .account(account)
            .build();

    transactionRepository.save(transaction);

    logger.info("Deposit successful for account {}. New balance: {}",
            account.getAccountNumber(),
            currentBalance);

    return DepositResponse.builder()
          .message(MessageConstants.AMOUNT_DEPOSITED)
            .accountNumber(account.getAccountNumber())
            .previousBalance(previousBalance)
            .depositedAmount(request.getAmount())
            .currentBalance(currentBalance)
            .build();
}

@Override
@Transactional
public WithdrawResponse withdraw(String accountNumber,
                                 WithdrawRequest request) {

    logger.info("Withdrawal initiated for account {} with amount {}",
            accountNumber,
            request.getAmount());

    Account account = getCustomerAccount(accountNumber);

    BigDecimal previousBalance = account.getBalance();

    // Check for sufficient balance
    if (previousBalance.compareTo(request.getAmount()) < 0) {

        logger.warn("Withdrawal failed due to insufficient balance. Account: {}, Available: {}, Requested: {}",
                accountNumber,
                previousBalance,
                request.getAmount());

       throw new InsufficientBalanceException(
        MessageConstants.INSUFFICIENT_BALANCE);
    }

    BigDecimal currentBalance =
            previousBalance.subtract(request.getAmount());

    account.setBalance(currentBalance);

    accountRepository.save(account);

    Transaction transaction = Transaction.builder()
            .transactionType(TransactionType.WITHDRAW)
            .amount(request.getAmount())
            .balanceAfterTransaction(currentBalance)
            .account(account)
            .build();

    transactionRepository.save(transaction);

    logger.info("Withdrawal successful for account {}. Remaining balance: {}",
            account.getAccountNumber(),
            currentBalance);

    return WithdrawResponse.builder()
           .message(MessageConstants.AMOUNT_WITHDRAWN)
            .accountNumber(account.getAccountNumber())
            .previousBalance(previousBalance)
            .withdrawnAmount(request.getAmount())
            .currentBalance(currentBalance)
            .build();
}

@Override
@Transactional
public TransferResponse transfer(TransferRequest request) {

    logger.info("Transfer initiated from account {} to account {} for amount {}",
            request.getFromAccountNumber(),
            request.getToAccountNumber(),
            request.getAmount());

    // Sender account must belong to logged-in user
    Account senderAccount =
            getCustomerAccount(request.getFromAccountNumber());

    // Sender and receiver cannot be same
    if (request.getFromAccountNumber()
            .equals(request.getToAccountNumber())) {

        logger.warn("Transfer rejected because sender and receiver accounts are the same: {}",
                request.getFromAccountNumber());

        throw new SameAccountTransferException(
        MessageConstants.SAME_ACCOUNT_TRANSFER);
    }

    // Receiver account can belong to anyone
    Account receiverAccount = accountRepository
            .findByAccountNumber(request.getToAccountNumber())
            .orElseThrow(() -> {
                logger.error("Receiver account not found: {}",
                        request.getToAccountNumber());

                return new AccountNotFoundException( MessageConstants.RECEIVER_ACCOUNT_NOT_FOUND);
      });

    BigDecimal amount = request.getAmount();

    BigDecimal senderBalance = senderAccount.getBalance();

    if (senderBalance.compareTo(amount) < 0) {

        logger.warn("Transfer failed due to insufficient balance. Sender Account: {}, Available: {}, Requested: {}",
                senderAccount.getAccountNumber(),
                senderBalance,
                amount);

        throw new InsufficientBalanceException(
        MessageConstants.INSUFFICIENT_BALANCE);
    }

    BigDecimal updatedSenderBalance =
            senderBalance.subtract(amount);

    BigDecimal updatedReceiverBalance =
            receiverAccount.getBalance().add(amount);

    senderAccount.setBalance(updatedSenderBalance);
    receiverAccount.setBalance(updatedReceiverBalance);

    accountRepository.save(senderAccount);
    accountRepository.save(receiverAccount);

    Transaction senderTransaction = Transaction.builder()
            .transactionType(TransactionType.TRANSFER)
            .amount(amount)
            .balanceAfterTransaction(updatedSenderBalance)
            .account(senderAccount)
            .build();

    Transaction receiverTransaction = Transaction.builder()
            .transactionType(TransactionType.TRANSFER)
            .amount(amount)
            .balanceAfterTransaction(updatedReceiverBalance)
            .account(receiverAccount)
            .build();

    transactionRepository.save(senderTransaction);
    transactionRepository.save(receiverTransaction);

    logger.info("Transfer successful. From: {} To: {} Amount: {}",
            senderAccount.getAccountNumber(),
            receiverAccount.getAccountNumber(),
            amount);

    return TransferResponse.builder()
            .message(MessageConstants.AMOUNT_TRANSFERRED)
            .fromAccountNumber(senderAccount.getAccountNumber())
            .toAccountNumber(receiverAccount.getAccountNumber())
            .transferredAmount(amount)
            .senderBalance(updatedSenderBalance)
            .receiverBalance(updatedReceiverBalance)
            .build();
}

@Override
public List<TransactionResponse> getTransactionHistory(String accountNumber) {

    logger.debug("Fetching transaction history for account {}",
            accountNumber);

    Account account = getCustomerAccount(accountNumber);

    List<Transaction> transactions =
            transactionRepository.findByAccountOrderByCreatedAtDesc(account);

    logger.info("Retrieved {} transaction(s) for account {}",
            transactions.size(),
            accountNumber);

    return transactions.stream()
            .map(transaction -> TransactionResponse.builder()
                    .transactionType(transaction.getTransactionType())
                    .amount(transaction.getAmount())
                    .balanceAfterTransaction(transaction.getBalanceAfterTransaction())
                    .createdAt(transaction.getCreatedAt())
                    .build())
            .toList();
}

@Override
public BalanceResponse getBalance(String accountNumber) {

    logger.debug("Fetching balance for account {}",
            accountNumber);

    Account account = getCustomerAccount(accountNumber);

    logger.info("Balance enquiry completed for account {}",
            accountNumber);

    return BalanceResponse.builder()
            .accountNumber(account.getAccountNumber())
            .balance(account.getBalance())
            .build();
}
}