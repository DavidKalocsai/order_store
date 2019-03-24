package com.intland.repository.util.procedure;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import com.intland.repository.util.mapper.OrderRowMapper;
import com.intland.repository.util.names.ProcedureNames;

@Service("simpleJdbcCreator")
public class SimpleJdbcCreatorImpl implements SimpleJdbcCreator {

  @Autowired
  private DataSource dataSource;

  @Override
  public SimpleJdbcCall createSimpleJdbcAddOrder() {
    return buildSimpleJdbcCall(ProcedureNames.getAddOrder());
  }

  @Override
  public SimpleJdbcCall createSimpleJdbcGetOrder() {
    return buildSimpleJdbcCall(ProcedureNames.getGetOrder());
  }

  @Override
  public SimpleJdbcCall createSimpleJdbcGetOrders() {
    return buildSimpleJdbcCall(ProcedureNames.getGetOrders());
  }

  @Override
  public SimpleJdbcCall createSimpleJdbcUpdateOrder() {
    return buildSimpleJdbcCall(ProcedureNames.getUpdateOrder());
  }

  @Override
  public SimpleJdbcCall createSimpleJdbcDeleteOrder() {
    return buildSimpleJdbcCall(ProcedureNames.getDeleteOrder());
  }

  private SimpleJdbcCall buildSimpleJdbcCall(final String procedureName) {
    return new SimpleJdbcCall(dataSource).withProcedureName(procedureName)
        .returningResultSet(ProcedureNames.getResultSetKey(), new OrderRowMapper());
  }



}
