package com.pluralsight.repository;

import java.util.List;
import com.pluralsight.model.OrderId;
import com.pluralsight.model.Order;

public interface OrderRepository {

  Order addOrder(Order ride);

  Order getOrder(OrderId id);

  List<Order> getOrders();

  Order updateOrder(Order order);

  void deleteOrder(OrderId id);
}
