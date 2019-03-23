package com.pluralsight.service;

import java.util.List;
import com.pluralsight.model.Id;
import com.pluralsight.model.Order;

public interface OrderService {

  Order addOrder(Order order);

  List<Order> getOrders();

  Order getOrder(Id id);

  Order updateOrder(Order order);

  void deleteOrder(Id id);
}
