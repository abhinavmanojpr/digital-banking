package com.digitalbanking.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {

    private String message;
    private String accessToken;
}