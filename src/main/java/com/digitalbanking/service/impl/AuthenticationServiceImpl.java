package com.digitalbanking.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digitalbanking.constants.MessageConstants;
import com.digitalbanking.constants.SecurityConstants;
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
import com.digitalbanking.exception.UserNotFoundException;
import com.digitalbanking.repository.UserRepository;
import com.digitalbanking.security.JwtService;
import com.digitalbanking.service.AuthenticationService;
import com.digitalbanking.util.AuthenticatedUserUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger logger =
            LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticatedUserUtil authenticatedUserUtil;

    @Override
    public LoginResponse login(LoginRequest request) {

        logger.info("Login attempt for email: {}", request.getEmail());

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    logger.warn("Login failed. Invalid email: {}", request.getEmail());
                    return new InvalidCredentialsException(
                            SecurityConstants.INVALID_CREDENTIALS);
                });

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {

            logger.warn("Login failed. Invalid password for email: {}",
                    request.getEmail());

            throw new InvalidCredentialsException(
                    SecurityConstants.INVALID_CREDENTIALS);
        }

        if (!user.getEnabled()) {

            logger.warn("Login failed. Account disabled for email: {}",
                    request.getEmail());

            throw new InvalidCredentialsException(
                    SecurityConstants.ACCOUNT_DISABLED);
        }

        String accessToken = jwtService.generateToken(user.getEmail());

        logger.info("Login successful for user: {}", user.getEmail());

        return LoginResponse.builder()
                .message(SecurityConstants.LOGIN_SUCCESSFUL)
                .accessToken(accessToken)
                .build();
    }

    @Override
    @Transactional
    public ChangePasswordResponse changePassword(ChangePasswordRequest request) {

        User user = authenticatedUserUtil.getAuthenticatedUser();

        logger.info("Password change requested for user: {}",
                user.getEmail());

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {

            logger.warn("Incorrect old password for user: {}",
                    user.getEmail());

            throw new OldPasswordIncorrectException(
                    SecurityConstants.OLD_PASSWORD_INCORRECT);
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {

            throw new PasswordsDoNotMatchException(
                    SecurityConstants.PASSWORDS_DO_NOT_MATCH);
        }

        if (passwordEncoder.matches(request.getNewPassword(), user.getPassword())) {

            throw new SamePasswordException(
                    SecurityConstants.SAME_PASSWORD);
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(user);

        logger.info("Password changed successfully for user: {}",
                user.getEmail());

        return ChangePasswordResponse.builder()
                .message(MessageConstants.PASSWORD_CHANGED)
                .build();
    }

    @Override
    public ForgotPasswordResponse forgotPassword(ForgotPasswordRequest request) {

        logger.info("Forgot password requested for email: {}",
                request.getEmail());

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new UserNotFoundException(
                                MessageConstants.USER_NOT_FOUND));

        String token = UUID.randomUUID().toString();

        user.setResetToken(token);
        user.setResetTokenExpiry(LocalDateTime.now().plusMinutes(15));

        userRepository.save(user);

        logger.info("Reset token generated for user: {}",
                user.getEmail());

        return ForgotPasswordResponse.builder()
                .message(MessageConstants.RESET_TOKEN_GENERATED)
                .resetToken(token)
                .build();
    }

    @Override
    public ResetPasswordResponse resetPassword(ResetPasswordRequest request) {

        logger.info("Password reset initiated using reset token.");

        User user = userRepository.findByResetToken(request.getToken())
                .orElseThrow(() ->
                        new InvalidResetTokenException(
                                SecurityConstants.INVALID_RESET_TOKEN));

        if (user.getResetTokenExpiry().isBefore(LocalDateTime.now())) {

            logger.warn("Expired reset token used by user: {}",
                    user.getEmail());

            throw new ExpiredResetTokenException(
                    SecurityConstants.RESET_TOKEN_EXPIRED);
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {

            throw new PasswordsDoNotMatchException(
                    SecurityConstants.PASSWORDS_DO_NOT_MATCH);
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        user.setResetToken(null);
        user.setResetTokenExpiry(null);

        userRepository.save(user);

        logger.info("Password reset completed for user: {}",
                user.getEmail());

        return ResetPasswordResponse.builder()
                .message(MessageConstants.PASSWORD_RESET)
                .build();
    }
}