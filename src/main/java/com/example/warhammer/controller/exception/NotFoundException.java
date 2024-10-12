package com.example.warhammer.controller.exception;

public class NotFoundException extends HttpException {
  public NotFoundException(String message) {
    super(message, 404);
  }
}
