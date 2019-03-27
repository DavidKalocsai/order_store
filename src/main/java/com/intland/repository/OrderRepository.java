package com.intland.repository;

import java.util.List;
import java.util.Optional;
import com.intland.model.Order;
import com.intland.model.OrderId;
import com.intland.model.OrderWithId;

public interface OrderRepository {

  Optional<OrderWithId> addOrder(Order ride);

  Optional<OrderWithId> updateOrder(OrderWithId order);

  Optional<OrderWithId> deleteOrder(OrderWithId order);

  Optional<OrderWithId> getOrder(OrderId id);

  List<OrderWithId> getOrders();

}
