package dev.trela.accounts.service;

import dev.trela.accounts.dto.CustomerDetailsDto;
import dev.trela.accounts.entity.Accounts;
import dev.trela.accounts.entity.Customer;

public interface CustomerService {
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId);
    Accounts getAccountByCustomerId(Long customerId);
    Customer getCustomerByMobileNumber(String mobileNumber);
}
