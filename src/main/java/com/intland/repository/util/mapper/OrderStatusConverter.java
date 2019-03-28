package com.intland.repository.util.mapper;

import org.springframework.stereotype.Service;
import com.intland.model.OrderStatus;

/**
 * Converter from OrderStatus to string and back.
 */
@Service
public class OrderStatusConverter implements Converter<String, OrderStatus> {

  /*
   * {@inheritDoc}
   */
  @Override
  public OrderStatus convert(String code) {
    OrderStatus orderStatus = null;
    for (final OrderStatus o : OrderStatus.values()) {
      if (orderStatus == null && o.getCode().equals(code)) {
        orderStatus = o;
      }
    }
    return orderStatus;
  }
}
