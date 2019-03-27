package com.intland.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.intland.model.Order;
import com.intland.model.OrderWithId;
import com.intland.service.OrderService;

@Controller
public class AddController extends ControllerExceptionHandler {

  private static final Logger LOG = LogManager.getLogger(AddController.class);

  @Autowired
  private OrderService orderService;

  @PostMapping(value = "/add")
  public @ResponseBody OrderWithId addOrder(@RequestBody Order order) {
    LOG.info("Add called: {}", order);
    return orderService.addOrder(order).orElse(null);
  }
}
