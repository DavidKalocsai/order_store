package com.intland.controller;

import javax.validation.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.intland.controller.util.error.ServiceError;
import com.intland.repository.exception.SqlCommandResultException;

public abstract class ControllerExceptionHandler {
  private static final Logger LOG = LogManager.getLogger(ControllerExceptionHandler.class);

  @ExceptionHandler(SqlCommandResultException.class)
  public ResponseEntity<ServiceError> handleSqlCommandException(
      final SqlCommandResultException ex) {
    LOG.error("SqlCommandResultException {}", ex.getMessage());
    return buildResponse(ex.getMessage());
  }

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ServiceError> handleValidationException(final ValidationException ex) {
    LOG.error("ValidationException {}", ex.getMessage());
    return buildResponse(ex.getMessage());
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ServiceError> handle(RuntimeException ex) {
    LOG.error("RuntimeException {}", ex.getMessage());
    return buildResponse(ex.getMessage());
  }

  private ResponseEntity<ServiceError> buildResponse(final String message) {
    ServiceError error = new ServiceError(HttpStatus.BAD_REQUEST.value(), message);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
}
