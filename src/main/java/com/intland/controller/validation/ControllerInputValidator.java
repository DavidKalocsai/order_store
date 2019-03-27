package com.intland.controller.validation;

import org.springframework.validation.BindingResult;


public interface ControllerInputValidator {

  void validateInput(BindingResult bindingResult);

}
