package edu.hhuc.cvuuhk.homeserver.handler;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ArgumentParseExceptionHandler {

  @ResponseBody
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public String handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    return e.getBindingResult().getFieldError().getDefaultMessage();
  }

  // todo：解析失败 handler
}
