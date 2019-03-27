package com.intland.repository.exception;

public class SqlCommandResultException extends RuntimeException {
  private static final long serialVersionUID = 6437010723307497806L;

  public SqlCommandResultException(final String msg) {
    super(msg);
  }
}
