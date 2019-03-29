package com.intland.repository.validate;

import java.util.Optional;
import com.intland.model.OrderDatabaseObj;

/**
 * Validate the result of update, delete, add.
 *
 */
public interface ProcedureResultValidator {
  /**
   * Validate the result of update, delete, add. If returnedOrder is null, exception will be thrown.
   *
   * @param returnedOrder {@link OrderDatabaseObj} object received from DB.
   * @return {@link OrderDatabaseObj}.
   */
  OrderDatabaseObj validate(Optional<OrderDatabaseObj> returnedOrder);
}
