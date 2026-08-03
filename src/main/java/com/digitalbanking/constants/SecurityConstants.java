package com.digitalbanking.constants;

public final class SecurityConstants {

    private SecurityConstants() {
        // Prevent instantiation
    }

    // ==========================
    // Authentication Messages
    // ==========================

    public static final String LOGIN_SUCCESSFUL =
            "Login successful";

    public static final String INVALID_CREDENTIALS =
            "Invalid email or password";

    public static final String ACCOUNT_DISABLED =
            "Account is disabled";

    // ==========================
    // Password Management
    // ==========================

    public static final String OLD_PASSWORD_INCORRECT =
            "Old password is incorrect";

    public static final String PASSWORDS_DO_NOT_MATCH =
            "New password and confirm password do not match";

    public static final String SAME_PASSWORD =
            "New password must be different from old password";

    // ==========================
    // Reset Password
    // ==========================

    public static final String INVALID_RESET_TOKEN =
            "Invalid reset token";

    public static final String RESET_TOKEN_EXPIRED =
            "Reset token has expired";
}