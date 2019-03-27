package com.intland.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.intland.model.OrderWithId;
import com.intland.service.OrderService;

@Controller
public class DeleteController extends ControllerExceptionHandler {

  @Autowired
  private OrderService orderService;

  @PutMapping(value = "/delete")
  public @ResponseBody OrderWithId delete(@RequestBody OrderWithId order) {
    return orderService.deleteOrder(order).orElse(null);
  }

}
