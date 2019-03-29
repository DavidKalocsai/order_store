package com.intland.repository.util.procedure;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import com.intland.model.Order;
import com.intland.model.OrderId;
import com.intland.model.OrderDatabaseObj;


public interface OrderProcedureParameterBuilder {

  SqlParameterSource getAddParameters(Order order);

  SqlParameterSource getGetParameters(OrderId id);

  SqlParameterSource getUpdateParameters(OrderDatabaseObj order);

  SqlParameterSource getDeleteParameters(OrderDatabaseObj order);

}
