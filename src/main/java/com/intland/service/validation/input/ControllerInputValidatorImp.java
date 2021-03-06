package com.intland.service.validation.input;

import javax.validation.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/*
 * ControllerInputValidatorImp acts on input validation errors.
 */
@Service
public class ControllerInputValidatorImp implements ControllerInputValidator {

  private static final Logger LOG = LogManager.getLogger(ControllerInputValidatorImp.class);

  /**
   * {@inheritDoc}
   */
  @Override
  public void validateInput(final BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      handleErrors(bindingResult);
    }
    LOG.info("Input validation passed");
  }

  private void handleErrors(final BindingResult bindingResult) {
    final StringBuilder stringBuilder = new StringBuilder();
    extractErrors(bindingResult, stringBuilder);
    notifyClient(stringBuilder);
  }

  private void extractErrors(final BindingResult bindingResult, final StringBuilder stringBuilder) {
    for (final FieldError error : bindingResult.getFieldErrors()) {
      stringBuilder.append(error.getDefaultMessage());
      stringBuilder.append(";");
    }
  }

  private void notifyClient(final StringBuilder stringBuilder) {
    final String errorMsg = stringBuilder.toString();
    LOG.error(errorMsg);
    throw new ValidationException(errorMsg);
  }
}
