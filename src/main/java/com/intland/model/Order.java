package com.intland.model;

import java.util.Date;
import com.google.common.base.MoreObjects;

public class Order {

  private Integer id;
  private String group;
  private Date date;
  private String description;
  private OrderStatus status;
  private Integer version;

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

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("Id", id).add("Group", group).add("Date", date)
        .add("Description", description).add("Status", status).add("Version", version).toString();
  }



}
