package dev.trela.accounts.service.impl;

import dev.trela.accounts.constants.AccountsConstants;
import dev.trela.accounts.dto.AccountsDto;
import dev.trela.accounts.dto.AccountsMsgDto;
import dev.trela.accounts.dto.CustomerDto;
import dev.trela.accounts.entity.Accounts;
import dev.trela.accounts.entity.Customer;
import dev.trela.accounts.exception.CustomerAlreadyExistsException;
import dev.trela.accounts.exception.ResourceNotFoundException;
import dev.trela.accounts.mapper.AccountsMapper;
import dev.trela.accounts.mapper.CustomerMapper;
import dev.trela.accounts.repository.AccountsRepository;
import dev.trela.accounts.repository.CustomerRepository;
import dev.trela.accounts.service.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final StreamBridge streamBridge;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto);
        // validate if customer already exists
        if(customerRepository.findByMobileNumber(customerDto.mobileNumber()).isPresent()){;
            throw new CustomerAlreadyExistsException("Customer already registered with given mobile Number: " + customerDto.mobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Annonymous");
        Customer savedCustomer = customerRepository.save(customer);
        Accounts savedAccount = accountsRepository.save(createNewAccount(savedCustomer));
        sendCommunication(savedAccount,savedCustomer);
    }

    private void sendCommunication(Accounts account, Customer customer){
        AccountsMsgDto accountsMsgDto = new AccountsMsgDto(account.getAccountNumber(), customer.getName(),
                customer.getEmail(),customer.getMobileNumber());
        log.info("Sending Communication request for the details {}" , accountsMsgDto);
        Boolean result = streamBridge.send("sendCommunication-out-0", accountsMsgDto);
        log.info("Is the communication request successfully triggered?: {}" , accountsMsgDto);
    }


    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        // validate if customer exists
        Customer customer = getCustomerByMobileNumber(mobileNumber);
        //validate if account exists for the customer
        Accounts accounts = getAccountByCustomerId(customer.getCustomerId());
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer,AccountsMapper.mapToAccountsDto(accounts));
        return customerDto;
    }

    @Override
    public CustomerDto updateAccount(CustomerDto customerDto) {
        Accounts accountToUpdate = getAccountById(customerDto.accountsDto().accountNumber());
        Customer customerToUpdate = findCustomerById(accountToUpdate.getCustomerId());

        customerToUpdate.setEmail(customerDto.email());
        customerToUpdate.setName(customerDto.name());
        customerToUpdate.setMobileNumber(customerDto.mobileNumber());


        accountToUpdate.setBranchAddress(customerDto.accountsDto().branchAddress());
        accountToUpdate.setAccountType(customerDto.accountsDto().accountType());

        Customer updatedCustomer = customerRepository.save(customerToUpdate);
        Accounts updatedAccount = accountsRepository.save(accountToUpdate);

        CustomerDto updatedCustomerDto = CustomerMapper.mapToCustomerDto(updatedCustomer, AccountsMapper.mapToAccountsDto(updatedAccount));
        return updatedCustomerDto;
    }

    @Override
    public Customer getCustomerByMobileNumber(String mobileNumber) {
        return customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()-> new ResourceNotFoundException("Customer", "moibleNumber", mobileNumber));
    }

    @Override
    public Accounts getAccountByCustomerId(Long customerId) {
        return accountsRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customerId.toString()));
    }

    @Override
    public Customer findCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId.toString()));
    }

    @Override
    public Accounts getAccountById(Long accountId) {
        return accountsRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "accountId", accountId.toString()));
    }

    @Override
    public void deleteAccount(String mobileNumber) {
        Customer customerToDelete = getCustomerByMobileNumber(mobileNumber);
        customerRepository.delete(customerToDelete);
    }

    @Override
    public boolean updateCommunicationStatus(Long accountNumber) {
        boolean isUpdated = false;
        if(accountNumber != null) {
            Accounts account = accountsRepository.findById(accountNumber).orElseThrow(()
                    -> new ResourceNotFoundException("Account", "accountNumber", accountNumber.toString()));

            account.setCommunicationSw(true);
            accountsRepository.save(account);

            isUpdated = true;
        }
            return isUpdated;
    }


    private Accounts createNewAccount(Customer customer){
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1_000_000_000L + new Random().nextInt(900_000_000);
        newAccount.setAccountNumber(randomAccNumber);
        //hardcoded values for simplicity
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);

        return newAccount;
    }



}
