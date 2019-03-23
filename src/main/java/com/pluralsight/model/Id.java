package com.pluralsight.model;

import com.google.common.base.MoreObjects;

public class Id {

  private Integer id;
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
