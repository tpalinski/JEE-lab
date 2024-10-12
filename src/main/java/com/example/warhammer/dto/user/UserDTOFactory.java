package com.example.warhammer.dto.user;

import com.example.warhammer.dto.user.function.AddRequestToUserFunction;
import com.example.warhammer.dto.user.function.UpdateRequestToUserFunction;
import com.example.warhammer.dto.user.function.UserToResponseFunction;
import com.example.warhammer.dto.user.function.UsersToResponseFunction;

public class UserDTOFactory {

  public UsersToResponseFunction usersToResponse() {
    return new UsersToResponseFunction();
  }

  public UserToResponseFunction userToResponse() {
    return new UserToResponseFunction();
  }

  public AddRequestToUserFunction addRequestToUser() {
    return new AddRequestToUserFunction();
  }

  public UpdateRequestToUserFunction updateRequestToUser() {
    return new UpdateRequestToUserFunction();
  }

}
