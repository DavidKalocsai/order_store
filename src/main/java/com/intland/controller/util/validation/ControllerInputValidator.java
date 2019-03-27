package com.intland.controller.util.validation;

import org.springframework.validation.BindingResult;


public interface ControllerInputValidator {

  void validateInput(BindingResult bindingResult);

}
