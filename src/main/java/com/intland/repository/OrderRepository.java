package com.intland.repository;

import java.util.List;
import java.util.Optional;
import com.intland.model.Order;
import com.intland.model.OrderDbObj;
import com.intland.model.OrderId;

/**
 * Order repository.
 */
public interface OrderRepository {

  /**
   * Add new order to database.
   *
   * @param order {@link Order}.
   * @return {@link OrderDbObj} return newly added order with DB information: id + group name.
   */
  OrderDbObj addOrder(Order order);

  /**
   * Update given order in database.
   *
   * @param order {@link OrderDbObj}.
   * @return {@link OrderDbObj} returns updated order.
   */
  OrderDbObj updateOrder(OrderDbObj order);

  /**
   * Delete new order in database.
   *
   * @param order {@link OrderDbObj}.
   * @return {@link OrderDbObj} returns updated order.
   */
  OrderDbObj deleteOrder(OrderDbObj order);

  /**
   * Get a specific order.
   *
   * @param id {@link OrderId}.
   * @return {@link OrderDbObj} returns optional with found Order or null.
   */
  Optional<OrderDbObj> getOrder(OrderId id);

  /**
   * Get all specific orders.
   *
   * @return List of {@link OrderDbObj} returns optional.
   */
  List<OrderDbObj> getOrders();

}
