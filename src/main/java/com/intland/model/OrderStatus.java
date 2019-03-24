package com.intland.model;

import com.google.common.base.MoreObjects;

public enum OrderStatus {
  ACTIVE("active", "Order is active!"), DELETED("deleted", "Order is deleted");

  private String code;
  private String description;

  private OrderStatus(final String code, final String description) {
    this.code = code;
    this.description = description;
  }

  public String getCode() {
    return code;
  }

  public String getDescription() {
    return description;
  }

  public static OrderStatus getEnumFromCode(final String code) {
    OrderStatus orderStatus = null;
    for (final OrderStatus o : OrderStatus.values()) {
      if (orderStatus == null && o.getCode().equals(code)) {
        orderStatus = o;
      }
    }
    return orderStatus;
  }


  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("DescriptiOn", description).toString();
  }

}
