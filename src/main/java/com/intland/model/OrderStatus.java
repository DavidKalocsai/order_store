package com.intland.model;

import com.google.common.base.MoreObjects;
import lombok.Getter;

/**
 * Status of an order. It can be active, or deleted. On delete order is only set to deleted, but not
 * removed.
 */
@Getter
public enum OrderStatus {
  ACTIVE("active", "Order is active!"), DELETED("deleted", "Order is deleted");

  private String code;
  private String description;

  OrderStatus(final String code, final String description) {
    this.code = code;
    this.description = description;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("DescriptiOn", description).toString();
  }

}
