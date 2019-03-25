package com.intland.repository.util.procedure;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;


public interface SimpleJdbcCreator {

  SimpleJdbcCall createSimpleJdbc(final String procedureName);

}
