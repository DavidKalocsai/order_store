package com.intland.model;

import javax.validation.constraints.Min;
import com.google.common.base.MoreObjects;

public class OrderId {

  @Min(value = 1, message = "{id.id.invalid}")
  private Integer id;

  @Min(value = 1, message = "{id.group.invalid=Group is invalid}")
  private String group;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }


  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("Id", id).add("Group", group).toString();
  }
}
