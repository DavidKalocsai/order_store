package com.intland.repository.validate;

import java.util.Optional;
import com.intland.model.OrderDbObj;


public interface ProcedureResultValidator {
  Optional<OrderDbObj> validate(Optional<OrderDbObj> returnedOrder);
}
