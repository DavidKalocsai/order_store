package com.intland.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.intland.model.Order;
import com.intland.service.OrderService;

@Controller
public class AddController extends ControllerExceptionHandler {

  @Autowired
  private OrderService orderService;

  @PostMapping(value = "/add")
  public @ResponseBody Order addOrder(@RequestBody Order order) {
    return orderService.addOrder(order).orElse(null);
  }
}
