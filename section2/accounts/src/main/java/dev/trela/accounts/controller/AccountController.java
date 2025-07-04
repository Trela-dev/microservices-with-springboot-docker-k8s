package dev.trela.accounts.controller;

import dev.trela.accounts.constants.AccountsConstants;
import dev.trela.accounts.dto.CustomerDto;
import dev.trela.accounts.dto.ErrorResponseDto;
import dev.trela.accounts.dto.ResponseDto;
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


//Tag for Swagger documentation
@Tag(
        name = "CRUD REST APIs for Accounts in TrelaBank",
        description = "CRUD REST APIs in TrelaBank to CREATE,UPDATE,FETCH AND DELETE accounts details"
)
@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class AccountController {


    private final AccountService accountService;


    // ApiResponse and Operation for Swagger documentation
    @ApiResponse(
            responseCode = "201",
            description= "HTTP Status CREATED"
    )
    @Operation(
            summary= "Create Account REST API",
            description = "REST API to create new Customer & Account in TrelaBank"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)


                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @ApiResponse(
            responseCode = "200",
            description= "HTTP Status OK"
    )
    @Operation(
            summary= "Fetch Account REST API",
            description = "REST API to fetch Customer & Account details in TrelaBank by mobile number"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam @Pattern(regexp = ("(^$|[0-9]{10})"), message = "Mobile number should be 10 digits") String mobileNumber){
        CustomerDto customerDto = accountService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerDto);
    }


    @ApiResponse(
            responseCode = "200",
            description= "HTTP Status OK",
            content=@Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )

    )
    @Operation(
            summary= "Update Account REST API",
            description = "REST API to update Customer & Account details in TrelaBank"
    )
    @PutMapping("/update")
    public ResponseEntity<CustomerDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.updateAccount(customerDto));
    }


    @ApiResponse(
            responseCode = "200",
            description= "HTTP Status OK"
    )
    @Operation(
            summary= "Delete Account REST API",
            description = "REST API to delete Customer & Account details in TrelaBank by mobile number"
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam @Pattern(regexp = ("(^$|[0-9]{10})"), message = "Mobile number should be 10 digits") String mobileNumber) {
        accountService.deleteAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
    }

}
