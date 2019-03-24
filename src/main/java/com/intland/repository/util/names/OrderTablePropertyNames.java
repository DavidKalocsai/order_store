package com.intland.repository.util.names;

public class OrderTablePropertyNames {
  private static final String TABLE_NAME = "order_table";
  private static final String ID = "order_id";
  private static final String GROUP_ID = "group_id";
  private static final String ORDER_DATE = "order_date";
  private static final String ORDER_DESC = "order_desc";
  private static final String ORDER_STATUS = "order_status";
  private static final String ORDER_VERSION = "order_version";

  private OrderTablePropertyNames() {

  }

  public static String getTableName() {
    return TABLE_NAME;
  }

  public static String getId() {
    return ID;
  }

  public static String getGroupId() {
    return GROUP_ID;
  }

  public static String getOrderDate() {
    return ORDER_DATE;
  }

  public static String getOrderDesc() {
    return ORDER_DESC;
  }

  public static String getOrderStatus() {
    return ORDER_STATUS;
  }

  public static String getOrderVersion() {
    return ORDER_VERSION;
  }
}
