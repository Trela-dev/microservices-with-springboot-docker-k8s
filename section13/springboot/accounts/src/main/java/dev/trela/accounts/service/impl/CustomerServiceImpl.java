package dev.trela.accounts.service.impl;

import dev.trela.accounts.dto.CardsDto;
import dev.trela.accounts.dto.CustomerDetailsDto;
import dev.trela.accounts.dto.CustomerDto;
import dev.trela.accounts.dto.LoanDto;
import dev.trela.accounts.entity.Accounts;
import dev.trela.accounts.entity.Customer;
import dev.trela.accounts.exception.ResourceNotFoundException;
import dev.trela.accounts.mapper.AccountsMapper;
import dev.trela.accounts.mapper.CustomerMapper;
import dev.trela.accounts.repository.AccountsRepository;
import dev.trela.accounts.repository.CustomerRepository;
import dev.trela.accounts.service.CustomerService;
import dev.trela.accounts.service.client.CardsFeignClient;
import dev.trela.accounts.service.client.LoansFeignClient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber,String correlationId) {
        // validate if customer exists
        Customer customer = getCustomerByMobileNumber(mobileNumber);
        //validate if account exists for the customer
        Accounts account = getAccountByCustomerId(customer.getCustomerId());



        ResponseEntity<LoanDto> loanDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId,mobileNumber);
        ResponseEntity<CardsDto> cardDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId,mobileNumber);

        LoanDto loanDto = null;
        CardsDto cardDto = null;

        if(loanDtoResponseEntity!= null) {
             loanDto = loanDtoResponseEntity.getBody();
        }

        if(cardDtoResponseEntity!= null) {
            cardDto = cardDtoResponseEntity.getBody();
        }


        return CustomerMapper
                .mapToCustomerDetailsDto(
                        customer,
                        AccountsMapper.mapToAccountsDto(account),
                        loanDto,
                        cardDto
                );
    }

    @Override
    public Accounts getAccountByCustomerId(Long customerId) {
        return accountsRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customerId.toString()));
    }

    @Override
    public Customer getCustomerByMobileNumber(String mobileNumber) {
        return customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()-> new ResourceNotFoundException("Customer", "moibleNumber", mobileNumber));
    }
}
