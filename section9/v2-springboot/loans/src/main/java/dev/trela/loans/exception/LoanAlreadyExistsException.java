package dev.trela.loans.exception;

public class LoanAlreadyExistsException extends RuntimeException {
  public LoanAlreadyExistsException(String message, String mobileNumber) {
    super(message + " " + mobileNumber);
  }
}
