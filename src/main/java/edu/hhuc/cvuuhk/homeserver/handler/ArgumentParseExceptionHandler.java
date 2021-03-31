package edu.hhuc.cvuuhk.homeserver.handler;

import edu.hhuc.cvuuhk.homeserver.exception.DeviceException;
import edu.hhuc.cvuuhk.homeserver.exception.InstructionException;
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

  @ResponseBody
  @ExceptionHandler(DeviceException.class)
  public String handleDeviceException(DeviceException e) {
    return e.getMessage();
  }

  @ResponseBody
  @ExceptionHandler(InstructionException.class)
  public String handleInstructionException(InstructionException e) {
    return e.getMessage();
  }
}
