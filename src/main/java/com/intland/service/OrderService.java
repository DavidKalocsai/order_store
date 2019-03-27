package com.intland.service;

import java.util.List;
import java.util.Optional;
import com.intland.model.Order;
import com.intland.model.OrderId;
import com.intland.model.OrderWithId;

public interface OrderService {

  Optional<OrderWithId> addOrder(Order order);

  List<OrderWithId> getOrders();

  Optional<OrderWithId> getOrder(OrderId id);

  Optional<OrderWithId> updateOrder(OrderWithId order);

  Optional<OrderWithId> deleteOrder(OrderWithId order);
}
