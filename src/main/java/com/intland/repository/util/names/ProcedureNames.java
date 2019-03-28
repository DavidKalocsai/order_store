package com.intland.repository.util.names;

/**
 * Domain object to store names of procedures.
 */
public class ProcedureNames {
  private static final String RESULT_SET_KEY = "orders";
  private static final String ADD_ORDER = "add_order";
  private static final String GET_ORDER = "get_order";
  private static final String GET_ORDERS = "get_orders";
  private static final String UPDATE_ORDER = "update_order";
  private static final String DELETE_ORDER = "delete_order";
  private static final String INPUT_PARAMETER_PREFIX = "i_";

  private ProcedureNames() {}

  public static String getAddOrder() {
    return ADD_ORDER;
  }

  public static String getGetOrder() {
    return GET_ORDER;
  }

  public static String getGetOrders() {
    return GET_ORDERS;
  }

  public static String getUpdateOrder() {
    return UPDATE_ORDER;
  }

  public static String getDeleteOrder() {
    return DELETE_ORDER;
  }

  public static String getResultSetKey() {
    return RESULT_SET_KEY;
  }

  public static String getInputParameter(final String columnName) {
    return INPUT_PARAMETER_PREFIX + columnName;
  }
}

