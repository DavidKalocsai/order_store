package com.intland.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.intland.model.Order;
import com.intland.model.OrderDbObj;
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
  public OrderDbObj addOrder(Order order) {
    return orderRepository.addOrder(order);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<OrderDbObj> getOrder(OrderId id) {
    return orderRepository.getOrder(id);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<OrderDbObj> getOrders() {
    return orderRepository.getOrders();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public OrderDbObj updateOrder(OrderDbObj order) {
    return orderRepository.updateOrder(order);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public OrderDbObj deleteOrder(OrderDbObj order) {
    return orderRepository.deleteOrder(order);
  }
}
