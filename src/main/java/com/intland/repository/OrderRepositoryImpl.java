package com.intland.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.intland.model.Order;
import com.intland.model.OrderDbObj;
import com.intland.model.OrderId;
import com.intland.repository.util.cast.OrderRowMapperResultExtractor;
import com.intland.repository.util.names.ProcedureNames;
import com.intland.repository.util.procedure.OrderProcedureParameterBuilder;
import com.intland.repository.util.procedure.SimpleJdbcCreator;
import com.intland.repository.validate.ProcedureResultValidator;

/**
 * Order repository.
 */
@Transactional
@Repository("orderRepository")
public class OrderRepositoryImpl implements OrderRepository {

  @Autowired
  private OrderProcedureParameterBuilder parameterBuilder;

  @Autowired
  private SimpleJdbcCreator simpleJdbcCreator;

  @Autowired
  private OrderRowMapperResultExtractor rowMapperResultExtractor;

  @Autowired
  private ProcedureResultValidator resultValidator;

  /**
   * {@inheritDoc}
   */
  @Override
  public OrderDbObj addOrder(final Order order) {
    return processModification(ProcedureNames.getAddOrder(),
        parameterBuilder.getAddParameters(order));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<OrderDbObj> getOrder(final OrderId id) {
    final SimpleJdbcCall simpleJdbcCall =
        simpleJdbcCreator.createSimpleJdbc(ProcedureNames.getGetOrder());
    return rowMapperResultExtractor
        .extractOrder(simpleJdbcCall.execute(parameterBuilder.getGetParameters(id)));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<OrderDbObj> getOrders() {
    final SimpleJdbcCall simpleJdbcCall =
        simpleJdbcCreator.createSimpleJdbc(ProcedureNames.getGetOrders());
    return rowMapperResultExtractor.extractOrders(simpleJdbcCall.execute());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public OrderDbObj updateOrder(final OrderDbObj order) {
    return processModification(ProcedureNames.getUpdateOrder(),
        parameterBuilder.getUpdateParameters(order));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public OrderDbObj deleteOrder(final OrderDbObj order) {
    return processModification(ProcedureNames.getDeleteOrder(),
        parameterBuilder.getDeleteParameters(order));
  }

  private OrderDbObj processModification(final String procedureName,
      final SqlParameterSource paramters) {
    final SimpleJdbcCall simpleJdbcCall = simpleJdbcCreator.createSimpleJdbc(procedureName);
    final Optional<OrderDbObj> orderOptional =
        rowMapperResultExtractor.extractOrder(simpleJdbcCall.execute(paramters));
    return resultValidator.validate(orderOptional);
  }
}
