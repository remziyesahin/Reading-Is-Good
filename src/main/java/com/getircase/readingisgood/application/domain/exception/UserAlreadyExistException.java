package com.getircase.readingisgood.application.domain.exception;

public class UserAlreadyExistException extends RuntimeException {
  private static final long serialVersionUID = 7246983447306271525L;

  public UserAlreadyExistException(String reason) {
    super(reason);
  }
}
