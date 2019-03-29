package com.intland.repository;

import java.util.List;
import java.util.Optional;
import com.intland.model.Order;
import com.intland.model.OrderDatabaseObj;
import com.intland.model.OrderId;

/**
 * Order repository.
 */
public interface OrderRepository {

  /**
   * Add new order to database.
   *
   * @param order {@link Order}.
   * @return {@link OrderDatabaseObj} return newly added order with DB information: id + group name.
   */
  OrderDatabaseObj addOrder(Order order);

  /**
   * Update given order in database.
   *
   * @param order {@link OrderDatabaseObj}.
   * @return {@link OrderDatabaseObj} returns updated order.
   */
  OrderDatabaseObj updateOrder(OrderDatabaseObj order);

  /**
   * Delete new order in database.
   *
   * @param order {@link OrderDatabaseObj}.
   * @return {@link OrderDatabaseObj} returns updated order.
   */
  OrderDatabaseObj deleteOrder(OrderDatabaseObj order);

  /**
   * Get a specific order.
   *
   * @param id {@link OrderId}.
   * @return {@link OrderDatabaseObj} returns optional with found Order or null.
   */
  Optional<OrderDatabaseObj> getOrder(OrderId id);

  /**
   * Get all specific orders.
   *
   * @return List of {@link OrderDatabaseObj} returns optional.
   */
  List<OrderDatabaseObj> getOrders();

}
