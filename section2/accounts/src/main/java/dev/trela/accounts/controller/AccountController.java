package dev.trela.accounts.controller;

import com.sun.jdi.VoidType;
import dev.trela.accounts.constants.AccountsConstants;

import dev.trela.accounts.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
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

}
