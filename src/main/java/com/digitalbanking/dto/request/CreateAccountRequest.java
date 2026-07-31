package com.digitalbanking.dto.request;

import java.math.BigDecimal;

import com.digitalbanking.enums.AccountType;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountRequest {

    @NotNull(message = "Account type is required")
    private AccountType accountType;

    @NotNull(message = "Initial deposit is required")
    @DecimalMin(value = "0.00", message = "Initial deposit cannot be negative")
    private BigDecimal initialDeposit;

}