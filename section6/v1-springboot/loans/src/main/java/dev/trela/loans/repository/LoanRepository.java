package dev.trela.loans.repository;

import dev.trela.loans.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface LoanRepository extends JpaRepository<Loan, String> {
    Optional<Loan> findByMobileNumber(String mobileNumber);
    void deleteByMobileNumber(String mobileNumber);
}
