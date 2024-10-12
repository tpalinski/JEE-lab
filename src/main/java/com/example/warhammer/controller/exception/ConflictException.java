package com.example.warhammer.controller.exception;

public class ConflictException extends HttpException {
  public ConflictException(String message) {
    super(message, 409);
  }
}
