package com.digitalbanking.service;

import com.digitalbanking.dto.request.CreateAccountRequest;
import com.digitalbanking.dto.request.DepositRequest;
import com.digitalbanking.dto.request.TransferRequest;
import com.digitalbanking.dto.request.WithdrawRequest;
import com.digitalbanking.dto.response.AccountDetailsResponse;
import com.digitalbanking.dto.response.AccountResponse;
import com.digitalbanking.dto.response.BalanceResponse;
import com.digitalbanking.dto.response.CreateAccountResponse;
import com.digitalbanking.dto.response.DepositResponse;
import com.digitalbanking.dto.response.TransactionResponse;
import com.digitalbanking.dto.response.TransferResponse;
import com.digitalbanking.dto.response.WithdrawResponse;

import java.util.List;


public interface AccountService {

    CreateAccountResponse createAccount(CreateAccountRequest request);
    List<AccountResponse> getMyAccounts();
    AccountDetailsResponse getAccount(String accountNumber);
    DepositResponse deposit(String accountNumber, DepositRequest request);
    WithdrawResponse withdraw(String accountNumber,WithdrawRequest request);
    TransferResponse transfer(TransferRequest request);
    List<TransactionResponse> getTransactionHistory(String accountNumber);
    BalanceResponse getBalance(String accountNumber);
}