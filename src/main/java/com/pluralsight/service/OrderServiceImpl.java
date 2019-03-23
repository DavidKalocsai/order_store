package com.pluralsight.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pluralsight.model.OrderId;
import com.pluralsight.model.Order;
import com.pluralsight.repository.OrderRepository;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Override
  public Order addOrder(Order ride) {
    return orderRepository.addOrder(ride);
  }

  @Override
  public Order getOrder(OrderId id) {
    return orderRepository.getOrder(id);
  }

  @Override
  public List<Order> getOrders() {
    return orderRepository.getOrders();
  }

  @Override
  public Order updateOrder(Order order) {
    return orderRepository.updateOrder(order);
  }

  @Override
  public void deleteOrder(OrderId id) {
    orderRepository.deleteOrder(id);
  }
}
