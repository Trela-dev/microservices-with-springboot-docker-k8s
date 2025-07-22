package dev.trela.loans.service;

import dev.trela.loans.dto.LoanDto;
import dev.trela.loans.model.Loan;

public interface LoanService {
    public LoanDto createLoan(String mobileNumber);
    LoanDto fetchLoanDetails(String mobileNumber);
    LoanDto updateLoanDetails(LoanDto loanDto);
    void deleteLoanDetails(String mobileNumber);
    Loan findByMobileNumber(String mobileNumber);
}
