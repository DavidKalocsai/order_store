package com.intland.repository.util.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.intland.model.Order;
import com.intland.model.OrderDatabaseObj;
import com.intland.model.OrderStatus;
import com.intland.repository.util.names.GroupTablePropertyNames;
import com.intland.repository.util.names.OrderTablePropertyNames;

/**
 * Map ResultSet to OrderDbObj.
 *
 */
public class OrderRowMapper implements RowMapper<Order> {

  @Override
  public OrderDatabaseObj mapRow(final ResultSet rs, final int rowNum) throws SQLException {
    final OrderDatabaseObj order = new OrderDatabaseObj();
    order.setId(rs.getInt(OrderTablePropertyNames.getId()));
    order.setGroupOrderId(rs.getInt(OrderTablePropertyNames.getGroupOrderId()));
    order.setGroup(rs.getString(GroupTablePropertyNames.getGroupName()));
    order.setDate(rs.getDate(OrderTablePropertyNames.getOrderDate()));
    order.setDescription(rs.getString(OrderTablePropertyNames.getOrderDesc()));
    order.setStatus(
        OrderStatus.getEnumFromCode(rs.getString(OrderTablePropertyNames.getOrderStatus())));
    order.setVersion(rs.getInt(OrderTablePropertyNames.getOrderVersion()));
    return order;
  }
}
