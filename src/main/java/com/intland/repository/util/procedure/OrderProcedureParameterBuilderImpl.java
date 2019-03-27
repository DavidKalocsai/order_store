package com.intland.repository.util.procedure;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import com.intland.model.Order;
import com.intland.model.OrderId;
import com.intland.model.OrderDbObj;
import com.intland.repository.util.names.GroupTablePropertyNames;
import com.intland.repository.util.names.OrderTablePropertyNames;
import com.intland.repository.util.names.ProcedureNames;

@Service("orderProcedureParameterBuilder")
public class OrderProcedureParameterBuilderImpl implements OrderProcedureParameterBuilder {

  @Override
  public SqlParameterSource getAddParameters(final Order order) {
    return new MapSqlParameterSource()
        .addValue(ProcedureNames.getInputParameter(GroupTablePropertyNames.getGroupName()),
            order.getGroup())
        .addValue(ProcedureNames.getInputParameter(OrderTablePropertyNames.getOrderDate()),
            order.getDate())
        .addValue(ProcedureNames.getInputParameter(OrderTablePropertyNames.getOrderDesc()),
            order.getDescription())
        .addValue(ProcedureNames.getInputParameter(OrderTablePropertyNames.getOrderStatus()),
            getStatus(order));
  }

  @Override
  public SqlParameterSource getGetParameters(final OrderId id) {
    return new MapSqlParameterSource()
        .addValue(ProcedureNames.getInputParameter(OrderTablePropertyNames.getId()), id.getId())
        .addValue(ProcedureNames.getInputParameter(GroupTablePropertyNames.getGroupName()),
            id.getGroup());
  }

  @Override
  public SqlParameterSource getUpdateParameters(final OrderDbObj order) {
    return new MapSqlParameterSource()
        .addValue(ProcedureNames.getInputParameter(OrderTablePropertyNames.getId()), order.getId())
        .addValue(ProcedureNames.getInputParameter(GroupTablePropertyNames.getGroupName()),
            order.getGroup())
        .addValue(ProcedureNames.getInputParameter(OrderTablePropertyNames.getOrderDate()),
            order.getDate())
        .addValue(ProcedureNames.getInputParameter(OrderTablePropertyNames.getOrderDesc()),
            order.getDescription())
        .addValue(ProcedureNames.getInputParameter(OrderTablePropertyNames.getOrderStatus()),
            getStatus(order))
        .addValue(ProcedureNames.getInputParameter(OrderTablePropertyNames.getOrderVersion()),
            order.getVersion());
  }

  @Override
  public SqlParameterSource getDeleteParameters(final OrderDbObj order) {
    return new MapSqlParameterSource()
        .addValue(ProcedureNames.getInputParameter(OrderTablePropertyNames.getId()), order.getId())
        .addValue(ProcedureNames.getInputParameter(GroupTablePropertyNames.getGroupName()),
            order.getGroup())
        .addValue(ProcedureNames.getInputParameter(OrderTablePropertyNames.getOrderVersion()),
            order.getVersion());
  }

  private String getStatus(final Order order) {
    return order.getStatus() != null ? order.getStatus().getCode() : null;
  }
}
