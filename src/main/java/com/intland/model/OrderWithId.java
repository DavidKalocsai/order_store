package com.intland.model;

import com.google.common.base.MoreObjects;

public class OrderWithId extends Order {

  private Integer id;
  private Integer version;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  @Override
  public String toString() {
    final String superString = super.toString();
    return MoreObjects.toStringHelper(this).add("Id", id).add("Version", version).toString()
        + superString;
  }
}
