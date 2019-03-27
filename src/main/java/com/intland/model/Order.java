package com.intland.model;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.google.common.base.MoreObjects;

public class Order {

  @NotBlank(message = "{order.group.invalid}")
  private String group;

  @NotNull(message = "{order.date.invalid}")
  private Date date;

  @NotBlank(message = "{order.description.invalid}")
  private String description;

  @NotNull(message = "{order.status.invalid}")
  private OrderStatus status;

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("Group", group).add("Date", date)
        .add("Description", description).add("Status", status).toString();
  }
}
