package com.pluralsight.repository.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.pluralsight.model.Order;
import com.pluralsight.model.OrderStatus;

public class OrderRowMapper implements RowMapper<Order> {

  @Override
  public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
    Order order = new Order();
    order.setId(rs.getInt("order_id"));
    order.setGroup(rs.getString("group_name"));
    order.setDate(rs.getDate("order_date"));
    order.setDescription(rs.getString("description"));
    order.setStatus(OrderStatus.getEnumFromCode(rs.getString("status")));
    return order;
  }
}
