package dev.trela.accounts.controller;

import dev.trela.accounts.dto.CustomerDetailsDto;
import dev.trela.accounts.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Validated
@RequiredArgsConstructor
public class CustomerController implements CustomerControllerApiDocumentation {

    private final CustomerService customerService;

    @Override
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(String mobileNumber) {
        return ResponseEntity.ok(customerService.fetchCustomerDetails(mobileNumber));
    }
}
