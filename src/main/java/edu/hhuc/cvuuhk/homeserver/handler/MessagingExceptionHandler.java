package edu.hhuc.cvuuhk.homeserver.handler;

import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MessagingExceptionHandler {
  @ExceptionHandler(MessagingException.class)
  public String handler(MessagingException e) {
    return e.getMessage();
  }
}
