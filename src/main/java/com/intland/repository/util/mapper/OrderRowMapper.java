package com.intland.repository.util.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import com.intland.model.Order;
import com.intland.model.OrderDbObj;
import com.intland.model.OrderStatus;
import com.intland.repository.util.names.GroupTablePropertyNames;
import com.intland.repository.util.names.OrderTablePropertyNames;

/**
 * Map ResultSet to OrderDbObj.
 *
 */
public class OrderRowMapper implements RowMapper<Order> {

  @Autowired
  private Converter<String, OrderStatus> orderStatusConverter;

  @Override
  public OrderDbObj mapRow(ResultSet rs, int rowNum) throws SQLException {
    OrderDbObj order = new OrderDbObj();
    order.setId(rs.getInt(OrderTablePropertyNames.getId()));
    order.setGroup(rs.getString(GroupTablePropertyNames.getGroupName()));
    order.setDate(rs.getDate(OrderTablePropertyNames.getOrderDate()));
    order.setDescription(rs.getString(OrderTablePropertyNames.getOrderDesc()));
    order.setStatus(
        orderStatusConverter.convert(rs.getString(OrderTablePropertyNames.getOrderStatus())));
    order.setVersion(rs.getInt(OrderTablePropertyNames.getOrderVersion()));
    return order;
  }
}
