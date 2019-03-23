package com.pluralsight.repository;

import java.util.List;
import com.pluralsight.model.Order;

public interface OrderRepository {

  Order addOrder(Order ride);

  List<Order> getOrders();

  Order getOrder(final Integer id, final String group);

  Order updateOrder(Order ride);

  void updateOrders(List<Order> pairs);

  void deleteOrder(Integer id);
}
