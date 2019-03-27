package com.intland.controller;

import javax.validation.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.intland.util.ServiceError;

public abstract class ControllerExceptionHandler {
  private static final Logger LOG = LogManager.getLogger(ControllerExceptionHandler.class);

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ServiceError> handleValidationException(final RuntimeException ex) {
    ServiceError error = new ServiceError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ServiceError> handle(RuntimeException ex) {
    ServiceError error = new ServiceError(HttpStatus.OK.value(), ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.OK);
  }
}
