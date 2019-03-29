package com.intland.repository.validate;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.intland.model.OrderDatabaseObj;
import com.intland.repository.exception.SqlCommandResultException;

/**
 * Validate the result of update, delete, add.
 *
 */
@Service
public class ProcedureResultValidatorImpl implements ProcedureResultValidator {
  private static final String RESULT_IS_NULL =
      "Failed to process command! Procedure returned with null!";

  /**
   * {@inheritDoc}
   */
  @Override
  public OrderDatabaseObj validate(final Optional<OrderDatabaseObj> returnedOrder) {
    if (!returnedOrder.isPresent()) {
      throw new SqlCommandResultException(RESULT_IS_NULL);
    }
    return returnedOrder.get();
  }
}
