package com.pluralsight.repository;

import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.pluralsight.model.OrderId;
import com.pluralsight.model.Order;
import com.pluralsight.repository.util.OrderRowMapper;

@Transactional
@Repository("orderRepository")
public class OrderRepositoryImpl implements OrderRepository {

  @Autowired
  private DataSource dataSource;

  @Override
  public Order addOrder(final Order order) {
    final SimpleJdbcCall simpleJdbcCall =
        new SimpleJdbcCall(dataSource).withProcedureName("add_order");
    final SqlParameterSource parameters =
        new MapSqlParameterSource().addValue("group_name", order.getGroup())
            .addValue("order_date", order.getDate()).addValue("description", order.getDescription())
            .addValue("status", order.getStatus() != null ? order.getStatus().getCode() : null);
    simpleJdbcCall.execute(parameters);

    return null;
  }

  @Override
  public Order getOrder(final OrderId id) {
    final SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource)
        .withProcedureName("get_order").returningResultSet("orders", new OrderRowMapper());
    SqlParameterSource parameters = new MapSqlParameterSource().addValue("id", id.getId())
        .addValue("group_name", id.getGroup());
    Map<String, Object> out = simpleJdbcCall.execute(parameters);

    return ((List<Order>) out.get("orders")).get(0);
  }

  @Override
  public List<Order> getOrders() {
    final SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource)
        .withProcedureName("get_orders").returningResultSet("orders", new OrderRowMapper());
    Map<String, Object> out = simpleJdbcCall.execute();

    return (List<Order>) out.get("orders");
  }

  @Override
  public Order updateOrder(Order order) {
    final SimpleJdbcCall simpleJdbcCall =
        new SimpleJdbcCall(dataSource).withProcedureName("update_order");
    final SqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("order_id", order.getId()).addValue("group_name", order.getGroup())
        .addValue("order_date", order.getDate()).addValue("description", order.getDescription())
        .addValue("status", order.getStatus() != null ? order.getStatus().getCode() : null);
    simpleJdbcCall.execute(parameters);

    return null;
  }

  @Override
  public void deleteOrder(final OrderId id) {
    final SimpleJdbcCall simpleJdbcCall =
        new SimpleJdbcCall(dataSource).withProcedureName("delete_order");
    final SqlParameterSource parameters = new MapSqlParameterSource().addValue("id", id.getId())
        .addValue("group_name", id.getGroup());
    simpleJdbcCall.execute(parameters);
  }
}
