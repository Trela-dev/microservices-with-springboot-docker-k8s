package dev.trela.accounts.controller;

import dev.trela.accounts.constants.AccountsConstants;

import dev.trela.accounts.service.AccountService;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import dev.trela.accounts.dto.*;

import java.util.concurrent.TimeoutException;

//Tag for Swagger documentation
@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
@Slf4j
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
    @Retry(name = "geldBuildInfo", fallbackMethod = "getBuildInfoFallback")
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() throws TimeoutException {
        log.debug("Invoked Accounts build-info API");

        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }

    public ResponseEntity<String> getBuildInfoFallback(Throwable t){
        log.debug("Invoked Accounts fallback method");
        return ResponseEntity.status(HttpStatus.OK).body("0.9");

    }

    @Override
    @RateLimiter(name = "getJavaVersion", fallbackMethod = "getJavaVersionFallback")
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    public ResponseEntity<String> getJavaVersionFallback(Throwable throwable){
        return ResponseEntity.status(HttpStatus.OK).body("Java 17");
    }


    @Override
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsContactInfoDto);
    }





}
