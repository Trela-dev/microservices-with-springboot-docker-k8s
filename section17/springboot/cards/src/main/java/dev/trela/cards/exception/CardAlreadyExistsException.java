package dev.trela.cards.exception;

public class CardAlreadyExistsException extends RuntimeException {
  public CardAlreadyExistsException(String message, String mobileNumber) {
    super(message + mobileNumber);
  }
}
