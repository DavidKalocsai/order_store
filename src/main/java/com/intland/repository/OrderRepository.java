package com.intland.repository;

import java.util.List;
import java.util.Optional;
import com.intland.model.Order;
import com.intland.model.OrderId;
import com.intland.model.OrderDbObj;

public interface OrderRepository {

  Optional<OrderDbObj> addOrder(Order ride);

  Optional<OrderDbObj> updateOrder(OrderDbObj order);

  Optional<OrderDbObj> deleteOrder(OrderDbObj order);

  Optional<OrderDbObj> getOrder(OrderId id);

  List<OrderDbObj> getOrders();

}
