package com.intland.controller;

import java.util.List;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.intland.model.OrderDbObj;
import com.intland.model.OrderId;
import com.intland.service.OrderService;
import com.intland.service.validation.input.ControllerInputValidator;

/**
 * Controller handles get one specific order or all.
 */
@Controller
public class GetController extends ControllerExceptionHandler {

  private static final Logger LOG = LogManager.getLogger(GetController.class);

  @Autowired
  private OrderService orderService;

  @Autowired
  private ControllerInputValidator validator;

  /**
   * Get an order based on OrderId (oder id + group name).
   *
   * @param id {@link OrderId}.
   * @param bindingResult in case of validation issue, it stores the error results, messages.
   * @return Return the requested order if found, else null.
   */
  @GetMapping(value = "/order")
  public @ResponseBody OrderDbObj getOrder(@Valid @RequestBody OrderId id,
      final BindingResult bindingResult) {
    LOG.info("Get order called: {}", id);
    return orderService.getOrder(id).orElse(null);
  }

  /**
   * Gets all order based
   *
   * @return Returns all orders if found any, else empty list.
   */
  @GetMapping(value = "/orders")
  public @ResponseBody List<OrderDbObj> getOrders() {
    LOG.info("Get orders called");
    return orderService.getOrders();
  }
}
