package com.intland.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.intland.model.Order;
import com.intland.model.OrderId;
import com.intland.repository.OrderRepository;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Override
  public Optional<Order> addOrder(Order order) {
    return orderRepository.addOrder(order);
  }

  @Override
  public Optional<Order> getOrder(OrderId id) {
    return orderRepository.getOrder(id);
  }

  @Override
  public List<Order> getOrders() {
    return orderRepository.getOrders();
  }

  @Override
  public Optional<Order> updateOrder(Order order) {
    return orderRepository.updateOrder(order);
  }

  @Override
  public Optional<Order> deleteOrder(Order order) {
    return orderRepository.deleteOrder(order);
  }
}
