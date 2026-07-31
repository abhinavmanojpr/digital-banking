package com.digitalbanking.service;

import com.digitalbanking.dto.request.CreateAccountRequest;
import com.digitalbanking.dto.response.AccountDetailsResponse;
import com.digitalbanking.dto.response.AccountResponse;
import com.digitalbanking.dto.response.CreateAccountResponse;
import java.util.List;


public interface AccountService {

    CreateAccountResponse createAccount(CreateAccountRequest request);
    List<AccountResponse> getMyAccounts();
    AccountDetailsResponse getAccount(String accountNumber);
}