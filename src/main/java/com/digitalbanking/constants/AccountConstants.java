package com.digitalbanking.constants;

import java.math.BigDecimal;

public final class AccountConstants {

    private AccountConstants() {
        // Prevent instantiation
    }

    // ==========================
    // Account Number
    // ==========================

    public static final long INITIAL_ACCOUNT_NUMBER = 1000000001L;

    // ==========================
    // Account Balance
    // ==========================

    public static final BigDecimal MINIMUM_BALANCE =
            BigDecimal.ZERO;

    // ==========================
    // Daily Limits
    // (Used in future sprints)
    // ==========================

    public static final BigDecimal DAILY_WITHDRAW_LIMIT =
            new BigDecimal("50000");

    public static final BigDecimal DAILY_TRANSFER_LIMIT =
            new BigDecimal("100000");

}