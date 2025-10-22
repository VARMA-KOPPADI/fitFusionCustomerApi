package com.fitFusion.customerApi.service;

import com.fitFusion.customerApi.dto.CustomerDto;
import com.fitFusion.customerApi.dto.PasswordUpdateDto;

public interface CustomerService {

    public CustomerDto saveCustomer(CustomerDto customerDto);
    public CustomerDto logIn(String email, String pwd);
    public boolean resetPwd(PasswordUpdateDto pwdUpdate);
    public CustomerDto getCustomerByEmail(String email);
    public CustomerDto updateCustomer(CustomerDto customerDto);
}
