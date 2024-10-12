package com.example.warhammer.controller.exception;

public class InternalServerErrorException extends HttpException {
  public InternalServerErrorException(String message) {
    super(message, 500);
  }
}
