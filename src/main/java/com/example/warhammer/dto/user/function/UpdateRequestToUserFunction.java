package com.example.warhammer.dto.user.function;

import java.util.function.BiFunction;

import com.example.warhammer.dto.user.UpdateUserRequest;
import com.example.warhammer.model.User;

public class UpdateRequestToUserFunction implements BiFunction<UpdateUserRequest, User, User> {
  @Override
  public User apply(UpdateUserRequest t, User u) {
    return User.builder()
      .id(u.getId())
      .name(t.getName())
      .login(u.getLogin())
      .rating(u.getRating())
      .build();
  }
}
