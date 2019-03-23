package com.pluralsight.repository;

import java.util.List;
import com.pluralsight.model.Id;
import com.pluralsight.model.Order;

public interface OrderRepository {

  Order addOrder(Order ride);

  Order getOrder(Id id);

  List<Order> getOrders();

  Order updateOrder(Order order);

  void deleteOrder(Id id);
}
