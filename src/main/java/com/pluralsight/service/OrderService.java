package com.pluralsight.service;

import java.util.List;
import com.pluralsight.model.Order;

public interface OrderService {

  Order addOrder(Order order);

  List<Order> getOrders();

  Order getOrder(Integer id, String group);

  Order updateOrder(Order ride);

  void deleteOrder(Integer id);
}
