package com.example.warhammer.controller.exception;

public class UnprocessableException extends HttpException {
  public UnprocessableException(String message) {
    super(message, 422);
  }
}
