package com.digitalbanking.dto.response;

import java.math.BigDecimal;

import com.digitalbanking.enums.AccountStatus;
import com.digitalbanking.enums.AccountType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccountResponse {

    private String accountNumber;

    private AccountType accountType;

    private BigDecimal balance;

    private AccountStatus status;

}