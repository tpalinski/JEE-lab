package com.example.warhammer.dto.user.function;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.example.warhammer.dto.user.GetUsersResponse;
import com.example.warhammer.model.User;

public class UsersToResponseFunction implements Function<List<User>, GetUsersResponse> {
  @Override
  public GetUsersResponse apply(List<User> t) {
    return GetUsersResponse.builder()
      .users(t
          .stream()
          .map((user) -> {
            return GetUsersResponse.User.builder()
              .login(user.getLogin())
              .id(user.getId())
              .build();
          })
          .collect(Collectors.toList())
      )
      .build();
  } 
}
