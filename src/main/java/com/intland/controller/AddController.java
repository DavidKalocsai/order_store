package com.intland.controller;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.intland.controller.validation.ControllerInputValidator;
import com.intland.model.Order;
import com.intland.model.OrderDbObj;
import com.intland.service.OrderService;

@Controller
public class AddController extends ControllerExceptionHandler {
  private static final Logger LOG = LogManager.getLogger(AddController.class);

  @Autowired
  private OrderService orderService;

  @Autowired
  private ControllerInputValidator validator;

  @PostMapping(value = "/add")
  public @ResponseBody OrderDbObj addOrder(@Valid @RequestBody final Order order,
      final BindingResult bindingResult) {
    LOG.info("Add called: {}", order);
    validator.validateInput(bindingResult);
    return orderService.addOrder(order).orElse(null);
  }
}
