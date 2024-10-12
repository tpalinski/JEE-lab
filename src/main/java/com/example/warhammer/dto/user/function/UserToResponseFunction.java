package com.example.warhammer.dto.user.function;

import java.util.function.Function;

import com.example.warhammer.dto.user.GetUserResponse;
import com.example.warhammer.model.User;

public class UserToResponseFunction implements Function<User, GetUserResponse>{
 @Override
  public GetUserResponse apply(User t) {
    return GetUserResponse.builder()
      .login(t.getLogin())
      .id(t.getId())
      .name(t.getName())
      .rating(t.getRating())
      .build();
  } 
}
