package com.digitalbanking.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawResponse {

    private String message;
    private String accountNumber;
    private BigDecimal previousBalance;
    private BigDecimal withdrawnAmount;
    private BigDecimal currentBalance;

}