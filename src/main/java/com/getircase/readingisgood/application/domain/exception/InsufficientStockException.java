package com.getircase.readingisgood.application.domain.exception;

public class InsufficientStockException extends RuntimeException {
  private static final long serialVersionUID = 7246983447306271525L;

  public InsufficientStockException(String reason) {
    super(reason);
  }
}
