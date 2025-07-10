package dev.trela.accounts.controller;

import dev.trela.accounts.constants.AccountsConstants;

import dev.trela.accounts.service.AccountService;

import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import dev.trela.accounts.dto.*;

//Tag for Swagger documentation
@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class AccountController implements AccountsApiDocumentation {

    private final AccountService accountService;

    @Value("${build.version}")
    private String buildVersion;
    private final Environment environment;
    private final AccountsContactInfoDto accountsContactInfoDto;

    // ApiResponse and Operation for Swagger documentation
    @Override
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @Override
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@Pattern(regexp = ("(^$|[0-9]{10})"), message = "Mobile number should be 10 digits") String mobileNumber){
        CustomerDto customerDto = accountService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerDto);
    }


    @Override
    @PutMapping("/update")
    public ResponseEntity<CustomerDto> updateAccountDetails(@RequestBody CustomerDto customerDto){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.updateAccount(customerDto));
    }


    @Override
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteAccount(@RequestParam String mobileNumber) {
        accountService.deleteAccount(mobileNumber);
        return ResponseEntity.noContent().build();
    }
    @Override
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo(){
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }

    @Override
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    @Override
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsContactInfoDto);
    }


}
