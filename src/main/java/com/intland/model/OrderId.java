package com.intland.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public class OrderId {

  @Min(value = 1, message = "{id.id.invalid}")
  private Integer id;

  @NotBlank(message = "{id.group.invalid}")
  private String group;

}
