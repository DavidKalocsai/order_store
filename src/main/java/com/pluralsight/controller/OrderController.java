package com.pluralsight.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pluralsight.model.Order;
import com.pluralsight.model.OrderId;
import com.pluralsight.service.OrderService;
import com.pluralsight.util.ServiceError;

@Controller
public class OrderController {

  @Autowired
  private OrderService orderService;

  @PostMapping(value = "/order")
  public @ResponseBody Order addOrder(@RequestBody Order order) {
    return orderService.addOrder(order);
  }

  @GetMapping(value = "/orders")
  public @ResponseBody List<Order> getOrders() {
    return orderService.getOrders();
  }

  @GetMapping(value = "/order")
  public @ResponseBody Order getOrder(@RequestBody OrderId id) {
    return orderService.getOrder(id);
  }

  @PutMapping(value = "/order")
  public @ResponseBody Order updateRide(@RequestBody Order order) {
    return orderService.updateOrder(order);
  }

  @PostMapping(value = "/delete")
  public @ResponseBody Object delete(@Valid @RequestBody OrderId id) {
    orderService.deleteOrder(id);
    return null;
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ServiceError> handle(RuntimeException ex) {
    ServiceError error = new ServiceError(HttpStatus.OK.value(), ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.OK);
  }

}
