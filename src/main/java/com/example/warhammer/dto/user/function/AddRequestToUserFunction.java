package com.example.warhammer.dto.user.function;

import java.util.function.Function;

import com.example.warhammer.dto.user.AddUserRequest;
import com.example.warhammer.model.User;

public class AddRequestToUserFunction implements Function<AddUserRequest, User> {
  @Override
  public User apply(AddUserRequest t) {
    return User.builder()
      .login(t.getLogin())
      .name(t.getName())
      .build();
  }
}
