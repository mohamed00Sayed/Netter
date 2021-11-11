package com.sayed.netter.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppWideExceptionHandler {

  @ExceptionHandler(DuplicateNettleException.class)
  public String handleNotFound() {
    return "error/duplicate";
  }

}
