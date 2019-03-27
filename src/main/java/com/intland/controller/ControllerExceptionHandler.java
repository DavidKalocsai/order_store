package com.intland.controller;

import javax.validation.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.intland.controller.util.error.ServiceError;
import com.intland.repository.exception.SqlCommandResultException;

/**
 * ControllerExceptionHandler is the base class of all controller. It handles exceptoin.
 */
public abstract class ControllerExceptionHandler {
  private static final Logger LOG = LogManager.getLogger(ControllerExceptionHandler.class);

  /**
   * Handles exception which is thrown when when add, delete or update function returns with null.
   *
   * @param ex {@link SqlCommandResultException}
   * @return response containing {@link ServiceError} object.
   */
  @ExceptionHandler(SqlCommandResultException.class)
  public ResponseEntity<ServiceError> handleSqlCommandException(
      final SqlCommandResultException ex) {
    LOG.error("SqlCommandResultException {}", ex.getMessage());
    return buildResponse(ex.getMessage());
  }

  /**
   * Handles exception which is thrown when hibernate validation fails on input parameter.
   *
   * @param ex {@link ValidationException}
   * @return response containing {@link ServiceError} object.
   */
  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ServiceError> handleValidationException(final ValidationException ex) {
    LOG.error("ValidationException {}", ex.getMessage());
    return buildResponse(ex.getMessage());
  }

  /**
   * Handles all other unchecked exception.
   *
   * @param ex {@link RuntimeException}
   * @return response containing {@link ServiceError} object.
   */
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
