package com.digitalbanking.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.digitalbanking.enums.TransactionType;

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
public class TransactionResponse {

    private TransactionType transactionType;

    private BigDecimal amount;

    private BigDecimal balanceAfterTransaction;

    private LocalDateTime createdAt;

}