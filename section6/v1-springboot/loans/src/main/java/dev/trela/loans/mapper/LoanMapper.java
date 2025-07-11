package dev.trela.loans.mapper;

import dev.trela.loans.dto.LoanDto;
import dev.trela.loans.model.Loan;

public class LoanMapper {

    public static LoanDto mapToLoanDto(Loan loan){
        return new LoanDto(
                loan.getMobileNumber(),
                loan.getLoanNumber(),
                loan.getLoanType(),
                loan.getTotalLoan(),
                loan.getAmountPaid(),
                loan.getOutstandingAmount()
        );
    }

    public static Loan mapToLoan(LoanDto loanDto){
        Loan loan = new Loan();
        loan.setMobileNumber(loanDto.mobileNumber());
        loan.setLoanNumber(loanDto.loanNumber());
        loan.setLoanType(loanDto.loanType());
        loan.setTotalLoan(loanDto.totalLoan());
        loan.setAmountPaid(loanDto.amountPaid());
        loan.setOutstandingAmount(loanDto.outstandingAmount());
        return loan;
    }


}
