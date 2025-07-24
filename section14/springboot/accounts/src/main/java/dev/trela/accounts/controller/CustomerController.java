package dev.trela.accounts.controller;

import dev.trela.accounts.dto.CustomerDetailsDto;
import dev.trela.accounts.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Validated
@RequiredArgsConstructor
@Log4j2
public class CustomerController implements CustomerControllerApiDocumentation {

    private final CustomerService customerService;

    @Override
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestHeader("trelaBank-correlation-id") String correlationId,@RequestParam String mobileNumber) {
        log.debug("fetchCustomerDetails method start");
        CustomerDetailsDto customerDetailsDto = (customerService.fetchCustomerDetails(mobileNumber,correlationId));
        log.debug("fetchCustomerDetails method end");
        return ResponseEntity.ok(customerDetailsDto);
    }
}
