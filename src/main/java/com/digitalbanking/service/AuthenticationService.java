package com.digitalbanking.service;

import com.digitalbanking.dto.request.ChangePasswordRequest;
import com.digitalbanking.dto.request.ForgotPasswordRequest;
import com.digitalbanking.dto.request.LoginRequest;
import com.digitalbanking.dto.request.ResetPasswordRequest;
import com.digitalbanking.dto.response.ChangePasswordResponse;
import com.digitalbanking.dto.response.ForgotPasswordResponse;
import com.digitalbanking.dto.response.LoginResponse;
import com.digitalbanking.dto.response.ResetPasswordResponse;

public interface AuthenticationService {

    LoginResponse login(LoginRequest request);

    ChangePasswordResponse changePassword(ChangePasswordRequest request);
    ForgotPasswordResponse forgotPassword(ForgotPasswordRequest request);
    ResetPasswordResponse resetPassword(ResetPasswordRequest request);

}