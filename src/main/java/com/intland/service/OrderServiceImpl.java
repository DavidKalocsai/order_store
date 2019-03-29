package com.intland.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.intland.model.Order;
import com.intland.model.OrderDatabaseObj;
import com.intland.model.OrderId;
import com.intland.repository.OrderRepository;

/**
 * Service layer to repository.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderRepository orderRepository;

  /**
   * {@inheritDoc}
   */
  @Override
  public OrderDatabaseObj addOrder(Order order) {
    return orderRepository.addOrder(order);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<OrderDatabaseObj> getOrder(OrderId id) {
    return orderRepository.getOrder(id);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<OrderDatabaseObj> getOrders() {
    return orderRepository.getOrders();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public OrderDatabaseObj updateOrder(OrderDatabaseObj order) {
    return orderRepository.updateOrder(order);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public OrderDatabaseObj deleteOrder(OrderDatabaseObj order) {
    return orderRepository.deleteOrder(order);
  }
}
