package com.intland.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.intland.util.ServiceError;

public class ControllerExceptionHandler {

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ServiceError> handle(RuntimeException ex) {
    ServiceError error = new ServiceError(HttpStatus.OK.value(), ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.OK);
  }
}
