package com.pluralsight.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pluralsight.model.Id;
import com.pluralsight.model.Order;
import com.pluralsight.service.OrderService;
import com.pluralsight.util.ServiceError;

@Controller
public class OrderController {

  @Autowired
  private OrderService orderService;

  @RequestMapping("/")
  public String index() {
    return "Greetings from Spring Boot!";
  }

  @RequestMapping(value = "/order", method = RequestMethod.POST)
  public @ResponseBody Order addOrder(@RequestBody Order order) {
    return orderService.addOrder(order);
  }

  @RequestMapping(value = "/orders", method = RequestMethod.GET)
  public @ResponseBody List<Order> getOrders() {
    return orderService.getOrders();
  }

  @RequestMapping(value = "/order", method = RequestMethod.GET)
  public @ResponseBody Order getOrder(@RequestBody Id id) {
    return orderService.getOrder(id);
  }

  @RequestMapping(value = "/order", method = RequestMethod.PUT)
  public @ResponseBody Order updateRide(@RequestBody Order order) {
    return orderService.updateOrder(order);
  }

  @RequestMapping(value = "/delete", method = RequestMethod.PUT)
  public @ResponseBody Object delete(@RequestBody Id id) {
    orderService.deleteOrder(id);
    return null;
  }

  @RequestMapping(value = "/test", method = RequestMethod.GET)
  public @ResponseBody Object test() {
    throw new IllegalStateException("Cannot connect the database!");
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ServiceError> handle(RuntimeException ex) {
    ServiceError error = new ServiceError(HttpStatus.OK.value(), ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.OK);
  }

}
