package com.intland.service;

import java.util.List;
import java.util.Optional;
import com.intland.model.Order;
import com.intland.model.OrderDbObj;
import com.intland.model.OrderId;

/**
 * Service layer to repository.
 */
public interface OrderService {

  /**
   * Add order.
   *
   * @param order {@link Order}
   * @return {@link OrderDbObj}
   */
  OrderDbObj addOrder(Order order);

  /**
   * Get orders.
   *
   * @return list of {@link OrderDbObj}
   */
  List<OrderDbObj> getOrders();

  /**
   * Add order.
   *
   * @param id {@link Order}
   * @return {@link OrderDbObj}
   */
  Optional<OrderDbObj> getOrder(OrderId id);

  /**
   * Update order.
   *
   * @param order {@link OrderDbObj}
   * @return {@link OrderDbObj}
   */
  OrderDbObj updateOrder(OrderDbObj order);

  /**
   * Delete order.
   *
   * @param order {@link OrderDbObj}
   * @return {@link OrderDbObj}
   */
  OrderDbObj deleteOrder(OrderDbObj order);
}
