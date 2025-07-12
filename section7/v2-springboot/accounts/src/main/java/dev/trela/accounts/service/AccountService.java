package dev.trela.accounts.service;

import dev.trela.accounts.dto.CustomerDto;
import dev.trela.accounts.entity.Accounts;
import dev.trela.accounts.entity.Customer;

public interface AccountService {
    void createAccount(CustomerDto customerDto);
    CustomerDto fetchAccount(String mobileNumber);
    CustomerDto updateAccount(CustomerDto customerDto);
    Customer getCustomerByMobileNumber(String mobileNumber);
    Accounts getAccountByCustomerId(Long customerId);
    Customer findCustomerById(Long customerId);
    Accounts getAccountById(Long accountId);
    void deleteAccount(String mobileNumber);


}
