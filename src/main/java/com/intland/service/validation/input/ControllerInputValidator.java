package com.intland.service.validation.input;

import org.springframework.validation.BindingResult;

/*
 * Interface to act on binding results (hibernate validator output).
 */
public interface ControllerInputValidator {

  /**
   * Checks if bindingResult has any errors. If yes, it gets all error message and throws
   * ValidationException.
   *
   * @param bindingResult {@link BindingResult}
   */
  void validateInput(BindingResult bindingResult);

}
