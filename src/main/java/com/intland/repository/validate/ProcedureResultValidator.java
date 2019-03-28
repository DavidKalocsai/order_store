package com.intland.repository.validate;

import java.util.Optional;
import com.intland.model.OrderDbObj;

/**
 * Validate the result of update, delete, add.
 *
 */
public interface ProcedureResultValidator {
  /**
   * Validate the result of update, delete, add. If returnedOrder is null, exception will be thrown.
   *
   * @param returnedOrder {@link OrderDbObj} object received from DB.
   * @return {@link OrderDbObj}.
   */
  OrderDbObj validate(Optional<OrderDbObj> returnedOrder);
}
