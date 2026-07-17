package com.digitalbanking.service;

import com.digitalbanking.dto.request.RegisterRequest;
import com.digitalbanking.dto.response.RegisterResponse;

public interface RegistrationService {

    RegisterResponse register(RegisterRequest request);

}