package com.intland.repository.util.names;

public class GroupTablePropertyNames {
  private static final String TABLE_NAME = "group_table";
  private static final String ID = "group_id";
  private static final String GROUP_NAME = "group_name";

  private GroupTablePropertyNames() {

  }

  public static String getTableName() {
    return TABLE_NAME;
  }

  public static String getId() {
    return ID;
  }

  public static String getGroupName() {
    return GROUP_NAME;
  }
}
