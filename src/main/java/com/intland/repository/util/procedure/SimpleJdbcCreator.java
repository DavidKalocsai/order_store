package com.intland.repository.util.procedure;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;


public interface SimpleJdbcCreator {

  SimpleJdbcCall createSimpleJdbcAddOrder();

  SimpleJdbcCall createSimpleJdbcGetOrder();

  SimpleJdbcCall createSimpleJdbcGetOrders();

  SimpleJdbcCall createSimpleJdbcUpdateOrder();

  SimpleJdbcCall createSimpleJdbcDeleteOrder();

}
