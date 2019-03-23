package com.pluralsight.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
  public Order getOrder(Integer id, String group) {
    return orderRepository.getOrder(id, group);
  }

  @Override
  public List<Order> getOrders() {
    return orderRepository.getOrders();
  }

  @Override
  public Order updateOrder(Order ride) {
    return orderRepository.updateOrder(ride);
  }

  @Override
  public void deleteOrder(Integer id) {
    orderRepository.deleteOrder(id);
  }
}
