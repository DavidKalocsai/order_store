package com.intland.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.intland.model.OrderId;
import com.intland.model.OrderDbObj;
import com.intland.service.OrderService;

@Controller
public class GetController extends ControllerExceptionHandler {

  @Autowired
  private OrderService orderService;

  @GetMapping(value = "/order")
  public @ResponseBody OrderDbObj getOrder(@RequestBody OrderId id) {
    return orderService.getOrder(id).orElse(null);
  }

  @GetMapping(value = "/orders")
  public @ResponseBody List<OrderDbObj> getOrders() {
    return orderService.getOrders();
  }
}
