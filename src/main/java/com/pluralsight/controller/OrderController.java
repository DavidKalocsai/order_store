package com.pluralsight.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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

  @RequestMapping(value = "/ride", method = RequestMethod.POST)
  public @ResponseBody Order addOrder(@RequestBody Order order) {
    return orderService.addOrder(order);
  }

  @RequestMapping(value = "/rides", method = RequestMethod.GET)
  public @ResponseBody List<Order> getRides() {
    return orderService.getOrders();
  }

  @RequestMapping(value = "/ride/{id}", method = RequestMethod.GET)
  public @ResponseBody Order getRide(@PathVariable(value = "id") Integer id) {
    return orderService.getOrder(id, "Name 1");
  }

  @RequestMapping(value = "/ride", method = RequestMethod.PUT)
  public @ResponseBody Order updateRide(@RequestBody Order ride) {
    return orderService.updateOrder(ride);
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
  public @ResponseBody Object delete(@PathVariable(value = "id") Integer id) {
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
