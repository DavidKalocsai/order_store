package com.intland.model;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Basic order object. It represents one simple order, no DB dependent information is stored.
 * 
 */
@Getter
@Setter
@ToString(includeFieldNames = true)
public class Order {

  @NotBlank(message = "{order.group.invalid}")
  private String group;

  @NotNull(message = "{order.date.invalid}")
  private Date date;

  @NotBlank(message = "{order.description.invalid}")
  private String description;

  @NotNull(message = "{order.status.invalid}")
  private OrderStatus status;

}
