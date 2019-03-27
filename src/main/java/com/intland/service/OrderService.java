package com.intland.service;

import java.util.List;
import java.util.Optional;
import com.intland.model.Order;
import com.intland.model.OrderId;
import com.intland.model.OrderDbObj;

public interface OrderService {

  Optional<OrderDbObj> addOrder(Order order);

  List<OrderDbObj> getOrders();

  Optional<OrderDbObj> getOrder(OrderId id);

  Optional<OrderDbObj> updateOrder(OrderDbObj order);

  Optional<OrderDbObj> deleteOrder(OrderDbObj order);
}
