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
public class TransferResponse {

    private String message;

    private String fromAccountNumber;

    private String toAccountNumber;

    private BigDecimal transferredAmount;

    private BigDecimal senderBalance;

    private BigDecimal receiverBalance;

}