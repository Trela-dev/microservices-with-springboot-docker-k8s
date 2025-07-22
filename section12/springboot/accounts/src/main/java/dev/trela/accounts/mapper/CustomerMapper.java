package dev.trela.accounts.mapper;

import dev.trela.accounts.dto.*;
import dev.trela.accounts.entity.Customer;

public class CustomerMapper {


    public static CustomerDto mapToCustomerDto(Customer customer, AccountsDto accountsDto) {
        return new CustomerDto(customer.getName(),customer.getEmail(),customer.getMobileNumber(),accountsDto);
    }


    public static CustomerDetailsDto mapToCustomerDetailsDto(Customer customer, AccountsDto accountsDto, LoanDto loanDto, CardsDto cardsDto) {
        return new CustomerDetailsDto(
                customer.getName(),
                customer.getEmail(),
                customer.getMobileNumber(),
                accountsDto,
                loanDto,
                cardsDto
        );
    }


    public static Customer mapToCustomer(CustomerDto customerDto) {
        return new Customer(customerDto.name(), customerDto.email(), customerDto.mobileNumber());

    }


}
