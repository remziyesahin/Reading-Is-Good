package com.getircase.readingisgood.application.domain.exception;

public class BookRetailObjectNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 7246983447306271525L;

  public BookRetailObjectNotFoundException(String reason) {
    super(reason);
  }
}
