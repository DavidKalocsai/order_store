package com.intland.repository;

import java.util.List;
import java.util.Optional;
import com.intland.model.Order;
import com.intland.model.OrderId;

public interface OrderRepository {

  Optional<Order> addOrder(Order ride);

  Optional<Order> updateOrder(Order order);

  Optional<Order> deleteOrder(Order order);

  Optional<Order> getOrder(OrderId id);

  List<Order> getOrders();

}
