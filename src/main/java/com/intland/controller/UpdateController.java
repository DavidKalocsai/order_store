package com.intland.controller;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.intland.model.OrderDatabaseObj;
import com.intland.service.OrderService;
import com.intland.service.validation.input.ControllerInputValidator;

/**
 * Controller handles update order requests.
 */
@Controller
public class UpdateController extends ControllerExceptionHandler {

  private static final Logger LOG = LogManager.getLogger(UpdateController.class);

  @Autowired
  private OrderService orderService;

  @Autowired
  private ControllerInputValidator validator;

  /**
   * Update order in database.
   *
   * @param order {@link OrderDatabaseObj}.
   * @param bindingResult in case of validation issue, it stores the error results, messages.
   * @return After successful update, order will be updated with the new values. In case of failures
   *         base class {@link ControllerExceptionHandler} will generate the response.
   */
  @PutMapping(value = "/update")
  public @ResponseBody OrderDatabaseObj updateOrder(@Valid @RequestBody OrderDatabaseObj order,
      final BindingResult bindingResult) {
    LOG.info("Update order called: {}", order);
    validator.validateInput(bindingResult);
    return orderService.updateOrder(order);
  }
}
