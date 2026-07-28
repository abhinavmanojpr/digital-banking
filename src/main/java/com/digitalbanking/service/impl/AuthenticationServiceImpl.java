package com.digitalbanking.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digitalbanking.dto.request.ChangePasswordRequest;
import com.digitalbanking.dto.request.ForgotPasswordRequest;
import com.digitalbanking.dto.request.LoginRequest;
import com.digitalbanking.dto.request.ResetPasswordRequest;
import com.digitalbanking.dto.response.ChangePasswordResponse;
import com.digitalbanking.dto.response.ForgotPasswordResponse;
import com.digitalbanking.dto.response.LoginResponse;
import com.digitalbanking.dto.response.ResetPasswordResponse;
import com.digitalbanking.entity.User;
import com.digitalbanking.exception.ExpiredResetTokenException;
import com.digitalbanking.exception.InvalidCredentialsException;
import com.digitalbanking.exception.InvalidResetTokenException;
import com.digitalbanking.exception.OldPasswordIncorrectException;
import com.digitalbanking.exception.PasswordsDoNotMatchException;
import com.digitalbanking.exception.SamePasswordException;
import com.digitalbanking.repository.UserRepository;
import com.digitalbanking.security.JwtService;
import com.digitalbanking.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        if (!user.getEnabled()) {
            throw new InvalidCredentialsException("Account is disabled");
        }

        String accessToken = jwtService.generateToken(user.getEmail());

        return LoginResponse.builder()
                .message("Login successful")
                .accessToken(accessToken)
                .build();
    }

    @Override
    @Transactional
    public ChangePasswordResponse changePassword(ChangePasswordRequest request) {

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new OldPasswordIncorrectException("Old password is incorrect");
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new PasswordsDoNotMatchException(
                    "New password and confirm password do not match");
        }

        if (passwordEncoder.matches(request.getNewPassword(), user.getPassword())) {
            throw new SamePasswordException(
                    "New password must be different from old password");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(user);

        return ChangePasswordResponse.builder()
                .message("Password changed successfully")
                .build();
    }

    @Override
    public ForgotPasswordResponse forgotPassword(ForgotPasswordRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = UUID.randomUUID().toString();

        user.setResetToken(token);
        user.setResetTokenExpiry(LocalDateTime.now().plusMinutes(15));

        userRepository.save(user);

        return ForgotPasswordResponse.builder()
                .message("Reset token generated")
                .resetToken(token)
                .build();
    }

    @Override
    public ResetPasswordResponse resetPassword(ResetPasswordRequest request) {

        User user = userRepository.findByResetToken(request.getToken())
                .orElseThrow(() ->
                        new InvalidResetTokenException("Invalid reset token"));

        if (user.getResetTokenExpiry().isBefore(LocalDateTime.now())) {
            throw new ExpiredResetTokenException("Reset token has expired");
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new PasswordsDoNotMatchException(
                    "New password and confirm password do not match");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        user.setResetToken(null);
        user.setResetTokenExpiry(null);

        userRepository.save(user);

        return ResetPasswordResponse.builder()
                .message("Password reset successfully")
                .build();
    }
}