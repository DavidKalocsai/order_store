package com.intland.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.intland.model.Order;
import com.intland.service.OrderService;

@Controller
public class UpdateController extends ControllerExceptionHandler {

  @Autowired
  private OrderService orderService;

  @PutMapping(value = "/update")
  public @ResponseBody Order updateOrder(@RequestBody Order order) {
    return orderService.updateOrder(order).orElse(null);
  }
}