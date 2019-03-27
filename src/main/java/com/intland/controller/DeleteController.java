package com.intland.controller;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.intland.controller.validation.ControllerInputValidator;
import com.intland.model.OrderDbObj;
import com.intland.service.OrderService;

@Controller
public class DeleteController extends ControllerExceptionHandler {

  private static final Logger LOG = LogManager.getLogger(DeleteController.class);

  @Autowired
  private OrderService orderService;

  @Autowired
  private ControllerInputValidator validator;

  @PutMapping(value = "/delete")
  public @ResponseBody OrderDbObj delete(@Valid @RequestBody OrderDbObj order,
      final BindingResult bindingResult) {
    LOG.info("Delete called: {}", order);
    validator.validateInput(bindingResult);
    return orderService.deleteOrder(order).orElse(null);
  }

}
