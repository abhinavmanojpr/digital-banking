package com.digitalbanking.dto.request;

import java.time.LocalDate;

public class RegisterRequest {

    private String email;
    private String password;

    private String firstName;
    private String lastName;

    private String phone;
    private LocalDate dateOfBirth;
    private String address;
}