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
 * Controller handles order delete requests.
 */
@Controller
public class DeleteController extends ControllerExceptionHandler {

  private static final Logger LOG = LogManager.getLogger(DeleteController.class);

  @Autowired
  private OrderService orderService;

  @Autowired
  private ControllerInputValidator validator;

  /**
   * Delete an order from the database. It does not delete a request, it just set it to inactive.
   *
   * @param order {@link OrderDatabaseObj}.
   * @param bindingResult in case of validation issue, it stores the error results, messages.
   * @return After successful add, order will be extended with Id and Version. In case of failures
   *         base class {@link ControllerExceptionHandler} will generate the response.
   */
  @PutMapping(value = "/delete")
  public @ResponseBody OrderDatabaseObj delete(@Valid @RequestBody OrderDatabaseObj order,
      final BindingResult bindingResult) {
    LOG.info("Delete called: {}", order);
    validator.validateInput(bindingResult);
    return orderService.deleteOrder(order);
  }

}
