package dev.trela.loans.controller;


import dev.trela.loans.dto.LoanDto;
import dev.trela.loans.dto.LoansContactInfoDto;
import dev.trela.loans.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
@Slf4j
public class LoansController implements LoansApiDocumentation {

    private final LoanService loanService;
    @Value("${build.version}")
    private String buildVersion;
    private final Environment environment;
    private final LoansContactInfoDto loansContactInfoDto;

    @Override
    @PostMapping("/create")
    public ResponseEntity<LoanDto> createLoan(@RequestParam String mobileNumber) {
        return ResponseEntity.
                status(HttpStatus.CREATED).body(loanService.createLoan(mobileNumber));
    }

    @Override
    @GetMapping("/fetch")
    public ResponseEntity<LoanDto> fetchLoanDetails(@RequestHeader("trelaBank-correlation-id") String correlationId,@RequestParam String mobileNumber) {
        log.debug("trelaBank-correlation-id found: {}", correlationId);
        return ResponseEntity
                .status(HttpStatus.OK).body(loanService.fetchLoanDetails(mobileNumber));
    }

    @Operation(summary = "Update Loan REST API", description = "REST API to update Loan details in TrelaBank")
    @Override
    @PutMapping("/update")
    public ResponseEntity<LoanDto> updateLoanDetails(@RequestBody LoanDto loanDto) {
        return ResponseEntity
                .status(HttpStatus.OK).body(loanService.updateLoanDetails(loanDto));
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteLoanDetails(@RequestParam String mobileNumber) {
        loanService.deleteLoanDetails(mobileNumber);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
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
    public ResponseEntity<LoansContactInfoDto> getLoansContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loansContactInfoDto);
    }


}
