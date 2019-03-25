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
  public SimpleJdbcCall createSimpleJdbc(final String procedureName) {
    return buildSimpleJdbcCall(procedureName);
  }

  private SimpleJdbcCall buildSimpleJdbcCall(final String procedureName) {
    return new SimpleJdbcCall(dataSource).withProcedureName(procedureName)
        .returningResultSet(ProcedureNames.getResultSetKey(), new OrderRowMapper());
  }
}
