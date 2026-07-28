package com.digitalbanking.service;

import com.digitalbanking.dto.response.CustomerProfileResponse;
import com.digitalbanking.dto.request.UpdateCustomerProfileRequest;
import com.digitalbanking.dto.request.ChangePasswordRequest;
import com.digitalbanking.dto.response.ChangePasswordResponse;

public interface CustomerService {

    CustomerProfileResponse getMyProfile();
    CustomerProfileResponse updateMyProfile(UpdateCustomerProfileRequest request);
    ChangePasswordResponse changePassword(ChangePasswordRequest request);
}