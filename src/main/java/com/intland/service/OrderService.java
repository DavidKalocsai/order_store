package com.intland.service;

import java.util.List;
import java.util.Optional;
import com.intland.model.Order;
import com.intland.model.OrderId;

public interface OrderService {

  Optional<Order> addOrder(Order order);

  List<Order> getOrders();

  Optional<Order> getOrder(OrderId id);

  Optional<Order> updateOrder(Order order);

  Optional<Order> deleteOrder(Order order);
}
