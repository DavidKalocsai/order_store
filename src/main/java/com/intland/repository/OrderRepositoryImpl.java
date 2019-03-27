package com.intland.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.intland.model.Order;
import com.intland.model.OrderId;
import com.intland.model.OrderDbObj;
import com.intland.repository.util.cast.OrderRowMapperResultExtractor;
import com.intland.repository.util.names.ProcedureNames;
import com.intland.repository.util.procedure.OrderProcedureParameterBuilder;
import com.intland.repository.util.procedure.SimpleJdbcCreator;

@Transactional
@Repository("orderRepository")
public class OrderRepositoryImpl implements OrderRepository {

  @Autowired
  private OrderProcedureParameterBuilder parameterBuilder;

  @Autowired
  private SimpleJdbcCreator simpleJdbcCreator;

  @Autowired
  private OrderRowMapperResultExtractor rowMapperResultExtractor;


  @Override
  public Optional<OrderDbObj> addOrder(final Order order) {
    return processCall(ProcedureNames.getAddOrder(), parameterBuilder.getAddParameters(order));
  }

  @Override
  public Optional<OrderDbObj> getOrder(final OrderId id) {
    return processCall(ProcedureNames.getGetOrder(), parameterBuilder.getGetParameters(id));
  }

  @Override
  public List<OrderDbObj> getOrders() {
    final SimpleJdbcCall simpleJdbcCall =
        simpleJdbcCreator.createSimpleJdbc(ProcedureNames.getGetOrders());
    return rowMapperResultExtractor.extractOrders(simpleJdbcCall.execute());
  }

  @Override
  public Optional<OrderDbObj> updateOrder(final OrderDbObj order) {
    return processCall(ProcedureNames.getUpdateOrder(),
        parameterBuilder.getUpdateParameters(order));
  }

  @Override
  public Optional<OrderDbObj> deleteOrder(final OrderDbObj order) {
    return processCall(ProcedureNames.getDeleteOrder(),
        parameterBuilder.getDeleteParameters(order));
  }

  private Optional<OrderDbObj> processCall(final String procedureName,
      final SqlParameterSource paramters) {
    final SimpleJdbcCall simpleJdbcCall = simpleJdbcCreator.createSimpleJdbc(procedureName);
    final Map<String, Object> out = simpleJdbcCall.execute(paramters);
    return rowMapperResultExtractor.extractOrder(out);
  }
}
