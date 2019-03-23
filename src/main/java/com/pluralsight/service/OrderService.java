package com.pluralsight.service;

import java.util.List;
import com.pluralsight.model.OrderId;
import com.pluralsight.model.Order;

public interface OrderService {

  Order addOrder(Order order);

  List<Order> getOrders();

  Order getOrder(OrderId id);

  Order updateOrder(Order order);

  void deleteOrder(OrderId id);
}
