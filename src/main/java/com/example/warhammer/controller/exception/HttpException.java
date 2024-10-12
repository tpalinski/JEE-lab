package com.example.warhammer.controller.exception;

import lombok.Getter;

@Getter
public class HttpException extends Exception {
  private final int status; 

  public HttpException(String message, int status) {
    super(message);
    this.status = status;
  }
}
