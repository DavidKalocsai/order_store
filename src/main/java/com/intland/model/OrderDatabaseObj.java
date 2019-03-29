package com.intland.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Basic order object extended with DB dependent fields: id, version.
 */
@Getter
@Setter
@ToString(callSuper = true, includeFieldNames = true)
public class OrderDatabaseObj extends Order {
  @NotNull(message = "{orderdb.id.invalid}")
  @Min(value = 1, message = "{orderdb.id.invalid}")
  private Integer id;

  @NotNull(message = "{orderdb.group.order.id.invalid}")
  @Min(value = 1, message = "{orderdb.group.order.id.invalid}")
  private Integer groupOrderId;

  @NotNull(message = "{orderdb.version.invalid}")
  @Min(value = 1, message = "{orderdb.version.invalid}")
  private Integer version;
}
