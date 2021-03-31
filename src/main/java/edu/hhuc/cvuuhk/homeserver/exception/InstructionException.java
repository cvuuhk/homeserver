package edu.hhuc.cvuuhk.homeserver.exception;

public class InstructionException extends RuntimeException {
  public InstructionException() {
    super();
  }

  public InstructionException(String message, Throwable cause) {
    super(message, cause);
  }

  public InstructionException(String message) {
    super(message);
  }

  public InstructionException(Throwable cause) {
    super(cause);
  }
}
