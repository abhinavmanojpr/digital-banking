package com.digitalbanking.service;

import com.digitalbanking.dto.request.LoginRequest;
import com.digitalbanking.dto.response.LoginResponse;

public interface AuthenticationService {

    LoginResponse login(LoginRequest request);

}