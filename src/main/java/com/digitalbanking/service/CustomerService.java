package com.digitalbanking.service;

import com.digitalbanking.dto.response.CustomerProfileResponse;
import com.digitalbanking.dto.request.UpdateCustomerProfileRequest;


public interface CustomerService {

    CustomerProfileResponse getMyProfile();
    CustomerProfileResponse updateMyProfile(UpdateCustomerProfileRequest request);
    
}