package com.intland.repository.validate;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.intland.model.OrderDbObj;
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
  public OrderDbObj validate(final Optional<OrderDbObj> returnedOrder) {
    if (!returnedOrder.isPresent()) {
      throw new SqlCommandResultException(RESULT_IS_NULL);
    }
    return returnedOrder.get();
  }
}
