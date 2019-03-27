package com.intland.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.google.common.base.MoreObjects;

public class OrderDbObj extends Order {

  @NotNull(message = "{orderdb.id.invalid}")
  @Min(value = 1, message = "{orderdb.id.invalid}")
  private Integer id;

  @NotNull(message = "{orderdb.version.invalid}")
  @Min(value = 1, message = "{orderdb.version.invalid}")
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
