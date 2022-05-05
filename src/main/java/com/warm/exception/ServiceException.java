package com.warm.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ServiceException extends RuntimeException {
  
  private static final long serialVersionUID = 1L;
  
  private Error error;
  
  public ServiceException(Error error) {
    this.error = error;
  }
  
  
  
  
}