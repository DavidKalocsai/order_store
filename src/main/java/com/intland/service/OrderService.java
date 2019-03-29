package com.intland.service;

import java.util.List;
import java.util.Optional;
import com.intland.model.Order;
import com.intland.model.OrderDatabaseObj;
import com.intland.model.OrderId;

/**
 * Service layer to repository.
 */
public interface OrderService {

  /**
   * Add order.
   *
   * @param order {@link Order}
   * @return {@link OrderDatabaseObj}
   */
  OrderDatabaseObj addOrder(Order order);

  /**
   * Get orders.
   *
   * @return list of {@link OrderDatabaseObj}
   */
  List<OrderDatabaseObj> getOrders();

  /**
   * Add order.
   *
   * @param id {@link Order}
   * @return {@link OrderDatabaseObj}
   */
  Optional<OrderDatabaseObj> getOrder(OrderId id);

  /**
   * Update order.
   *
   * @param order {@link OrderDatabaseObj}
   * @return {@link OrderDatabaseObj}
   */
  OrderDatabaseObj updateOrder(OrderDatabaseObj order);

  /**
   * Delete order.
   *
   * @param order {@link OrderDatabaseObj}
   * @return {@link OrderDatabaseObj}
   */
  OrderDatabaseObj deleteOrder(OrderDatabaseObj order);
}
