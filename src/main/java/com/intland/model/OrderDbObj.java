package com.intland.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true, includeFieldNames = true)
public class OrderDbObj extends Order {

  @NotNull(message = "{orderdb.id.invalid}")
  @Min(value = 1, message = "{orderdb.id.invalid}")
  private Integer id;

  @NotNull(message = "{orderdb.version.invalid}")
  @Min(value = 1, message = "{orderdb.version.invalid}")
  private Integer version;
}
