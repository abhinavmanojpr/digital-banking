package com.digitalbanking.constants;

public final class MessageConstants {

    private MessageConstants() {
        // Prevent instantiation
    }

    // Success Messages
    public static final String ACCOUNT_CREATED =
            "Account created successfully";

    public static final String AMOUNT_DEPOSITED =
            "Amount deposited successfully";

    public static final String AMOUNT_WITHDRAWN =
            "Amount withdrawn successfully";

    public static final String AMOUNT_TRANSFERRED =
            "Amount transferred successfully";

    public static final String PASSWORD_CHANGED =
            "Password changed successfully";

    public static final String PASSWORD_RESET =
            "Password reset successfully";

        public static final String RESET_TOKEN_GENERATED =
        "Reset token generated";

    public static final String REGISTRATION_SUCCESSFUL =
            "Registration successful";
            

    // Error Messages
    public static final String USER_NOT_FOUND =
            "User not found";

    public static final String CUSTOMER_NOT_FOUND =
            "Customer not found";

    public static final String ACCOUNT_NOT_FOUND =
            "Account not found";

    public static final String RECEIVER_ACCOUNT_NOT_FOUND =
            "Receiver account not found";

    public static final String INSUFFICIENT_BALANCE =
            "Insufficient account balance";

    public static final String UNAUTHORIZED_ACCOUNT_ACCESS =
            "You are not authorized to access this account";

    public static final String SAME_ACCOUNT_TRANSFER =
            "Sender and receiver account cannot be the same";

    public static final String PHONE_ALREADY_EXISTS =
        "Phone number already exists";
}