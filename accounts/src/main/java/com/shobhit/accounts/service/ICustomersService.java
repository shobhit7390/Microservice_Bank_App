package com.shobhit.accounts.service;

import com.shobhit.accounts.dto.CustomerDetailsDto;

public interface ICustomersService {

    CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId);
}
