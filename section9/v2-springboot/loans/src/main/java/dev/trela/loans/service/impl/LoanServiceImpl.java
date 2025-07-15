package dev.trela.loans.service.impl;

import dev.trela.loans.constants.LoanConstants;
import dev.trela.loans.dto.LoanDto;
import dev.trela.loans.exception.LoanAlreadyExistsException;
import dev.trela.loans.exception.ResourceNotFoundException;
import dev.trela.loans.mapper.LoanMapper;
import dev.trela.loans.model.Loan;
import dev.trela.loans.repository.LoanRepository;
import dev.trela.loans.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    @Override
    public LoanDto createLoan(String mobileNumber) {
        if(loanRepository.findByMobileNumber(mobileNumber).isPresent())
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber ",mobileNumber);
        return LoanMapper.mapToLoanDto(loanRepository.save(createNewLoan(mobileNumber)));
    }


    private Loan createNewLoan(String mobileNumber) {
        Loan newLoan = new Loan();
        long randomLoanNumber = 100_000_000_000L + new Random().nextInt(900_000_000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoanConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }



    @Override
    public LoanDto fetchLoanDetails(String mobileNumber) {
        return LoanMapper.mapToLoanDto(findByMobileNumber(mobileNumber));
    }

    @Override
    public LoanDto updateLoanDetails(LoanDto loanDto) {
        String mobileNumber = loanDto.mobileNumber();
        Loan loanToUpdate = findByMobileNumber(mobileNumber);

        loanToUpdate.setLoanNumber(loanDto.loanNumber());
        loanToUpdate.setLoanNumber(loanDto.loanNumber());
        loanToUpdate.setLoanType(loanDto.loanType());
        loanToUpdate.setTotalLoan(loanDto.totalLoan());
        loanToUpdate.setAmountPaid(loanDto.amountPaid());
        loanToUpdate.setOutstandingAmount(loanDto.outstandingAmount());

        return LoanMapper.mapToLoanDto(loanRepository.save(loanToUpdate));
    }

    @Override
    public void deleteLoanDetails(String mobileNumber) {
        findByMobileNumber(mobileNumber);
        loanRepository.deleteByMobileNumber(mobileNumber);
    }

    @Override
    public Loan findByMobileNumber(String mobileNumber) {
        return loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan","mobileNumber",mobileNumber)
        );
    }
}
