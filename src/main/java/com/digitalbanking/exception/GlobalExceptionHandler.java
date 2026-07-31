package com.digitalbanking.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handles duplicate email
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(
            EmailAlreadyExistsException ex) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(
                errorResponse,
                HttpStatus.CONFLICT
        );
    }

    // Handles invalid login credentials
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(
            InvalidCredentialsException ex) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.UNAUTHORIZED.value())
                .error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(
                errorResponse,
                HttpStatus.UNAUTHORIZED
        );
    }

    // Handles @Valid validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError ->
                        errors.put(
                                fieldError.getField(),
                                fieldError.getDefaultMessage()
                        )
                );

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Validation failed")
                .errors(errors)
                .build();

        return new ResponseEntity<>(
                errorResponse,
                HttpStatus.BAD_REQUEST
        );
    }
        //handles duplicate phone number
        @ExceptionHandler(PhoneAlreadyExistsException.class)
        public ResponseEntity<ErrorResponse> handlePhoneAlreadyExistsException(
                PhoneAlreadyExistsException ex) {

          ErrorResponse error = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .message(ex.getMessage())
                .build();

                return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }

        //handles old pwd incorrrect
        @ExceptionHandler(OldPasswordIncorrectException.class)
        public ResponseEntity<ErrorResponse> handleOldPasswordIncorrectException(
                OldPasswordIncorrectException ex) {

          ErrorResponse error = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        //handles password do not  match
        @ExceptionHandler(PasswordsDoNotMatchException.class)
        public ResponseEntity<ErrorResponse> handlePasswordsDoNotMatchException(
                PasswordsDoNotMatchException ex) {

          ErrorResponse error = ErrorResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .message(ex.getMessage())
                    .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        //handles same password
        @ExceptionHandler(SamePasswordException.class)
        public ResponseEntity<ErrorResponse> handleSamePasswordException(
                SamePasswordException ex) {

          ErrorResponse error = ErrorResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .message(ex.getMessage())
                    .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(InvalidResetTokenException.class)
        public ResponseEntity<ErrorResponse> handleInvalidResetTokenException(
                InvalidResetTokenException ex) {

        ErrorResponse error = ErrorResponse.builder()
                    .message(ex.getMessage())
                    .status(HttpStatus.BAD_REQUEST.value())
                    .timestamp(LocalDateTime.now())
                    .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }


        @ExceptionHandler(ExpiredResetTokenException.class)
        public ResponseEntity<ErrorResponse> handleExpiredResetTokenException(
                ExpiredResetTokenException ex) {

        ErrorResponse error = ErrorResponse.builder()
                    .message(ex.getMessage())
                    .status(HttpStatus.BAD_REQUEST.value())
                    .timestamp(LocalDateTime.now())
                    .build();

                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(UserNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleUserNotFoundException(
                UserNotFoundException ex) {

                ErrorResponse error = ErrorResponse.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .error("Not Found")
                    .message(ex.getMessage())
                    .build();

                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(CustomerNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(
                CustomerNotFoundException ex) {

                ErrorResponse error = ErrorResponse.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .error("Not Found")
                    .message(ex.getMessage())
                    .build();

                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(AccountNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleAccountNotFoundException(
                AccountNotFoundException ex) {

        ErrorResponse error = ErrorResponse.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .error("Not Found")
                    .message(ex.getMessage())
                    .timestamp(LocalDateTime.now())
                    .build();

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(UnauthorizedAccountAccessException.class)
        public ResponseEntity<ErrorResponse> handleUnauthorizedAccountAccessException(
                UnauthorizedAccountAccessException ex) {

        ErrorResponse error = ErrorResponse.builder()
                    .status(HttpStatus.FORBIDDEN.value())
                    .error("Forbidden")
                    .message(ex.getMessage())
                    .timestamp(LocalDateTime.now())
                    .build();

        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
        }
}