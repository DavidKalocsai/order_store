package com.intland.model;

import javax.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public class OrderId {

  @Min(value = 1, message = "{id.id.invalid}")
  private Integer id;

  @Min(value = 1, message = "{id.group.invalid=Group is invalid}")
  private String group;

}
