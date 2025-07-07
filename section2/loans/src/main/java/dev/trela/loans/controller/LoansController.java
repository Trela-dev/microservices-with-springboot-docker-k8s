package dev.trela.loans.controller;


import dev.trela.loans.dto.LoanDto;
import dev.trela.loans.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class LoansController implements LoansApiDocumentation {

    private final LoanService loanService;

    @Override
    @PostMapping("/create")
    public ResponseEntity<LoanDto> createLoan(@RequestParam String mobileNumber) {
        return ResponseEntity.
                status(HttpStatus.CREATED).body(loanService.createLoan(mobileNumber));
    }

    @Override
    @GetMapping("/fetch")
    public ResponseEntity<LoanDto> fetchLoanDetails(@RequestParam String mobileNumber) {
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


}
