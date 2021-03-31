package edu.hhuc.cvuuhk.homeserver.exception;

public class DeviceException extends RuntimeException {
  public DeviceException() {
    super();
  }

  public DeviceException(String message, Throwable cause) {
    super(message, cause);
  }

  public DeviceException(String message) {
    super(message);
  }

  public DeviceException(Throwable cause) {
    super(cause);
  }
}
