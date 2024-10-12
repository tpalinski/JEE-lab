package com.example.warhammer.controller.user;

import java.util.Optional;
import java.util.UUID;

import com.example.warhammer.config.AppConfig;
import com.example.warhammer.controller.exception.ConflictException;
import com.example.warhammer.controller.exception.HttpException;
import com.example.warhammer.controller.exception.InternalServerErrorException;
import com.example.warhammer.controller.exception.NotFoundException;
import com.example.warhammer.dto.user.AddUserRequest;
import com.example.warhammer.dto.user.GetUserResponse;
import com.example.warhammer.dto.user.GetUsersResponse;
import com.example.warhammer.dto.user.UpdateUserRequest;
import com.example.warhammer.dto.user.UserDTOFactory;
import com.example.warhammer.model.User;
import com.example.warhammer.repository.exception.EntityExistsException;
import com.example.warhammer.repository.exception.NonexistentEntityException;
import com.example.warhammer.service.user.UserService;
import com.example.warhammer.storage.exception.StorageException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SimpleUserController implements UserController {
  private UserService userService;
  private UserDTOFactory userDTOFactory;
  private AppConfig config;

  @Override
  public GetUsersResponse getUsers() {
    return userDTOFactory
      .usersToResponse()
      .apply(userService.getUsers());
  }

  @Override
  public GetUserResponse getUser(UUID id) throws NotFoundException {
    Optional<User> res = userService.getUser(id);
    if (res.isEmpty()) {
      throw new NotFoundException("No user with provided id");
    }
    return userDTOFactory
      .userToResponse()
      .apply(res.get());
  }

  @Override
  public UUID addUser(AddUserRequest request) throws ConflictException {
    try {
      User added = userDTOFactory
        .addRequestToUser()
        .apply(request);
      UUID id = UUID.randomUUID();
      added.setRating(config.getInitialUserRating());
      added.setId(id);
      userService.addUser(added);
      return id;
    } catch (EntityExistsException e) {
      throw new ConflictException("User with this id already exists");
    }
  }

  @Override
  public void deleteUser(UUID id) throws NotFoundException { 
    try {
      userService.deleteUser(id);
    } catch (NonexistentEntityException e) {
      throw new NotFoundException("No user with provided id");
    }
  }

  @Override
  public void updateUser(UUID id, UpdateUserRequest request) throws NotFoundException{
    Optional<User> existing = userService.getUser(id);
    if (existing.isEmpty()) {
      throw new NotFoundException("No user with provided id");
    }
    User newUser = userDTOFactory
      .updateRequestToUser()
      .apply(request, existing.get());
    try {
      userService.updateUser(newUser);
    } catch (NonexistentEntityException e) {
      throw new NotFoundException("Could not update nonexistent user");
    }
  }

@Override
public byte[] getUserAvatar(UUID id) throws HttpException {
  Optional<byte[]> res = userService.getAvatar(id);
  if (res.isEmpty()) {
    throw new NotFoundException("No avatar for user");
  }
  return res.get();
}

@Override
public void deleteUserAvatar(UUID id) throws HttpException {
	try {
    userService.deleteAvatar(id);
  } catch(StorageException e) {
    throw new NotFoundException("No avatar for user");
  }
}

  @Override
  public void updateUserAvatar(UUID id, byte[] content) throws HttpException {
    try {
      userService.updateAvatar(id, content);
    } catch(StorageException e) {
      throw new InternalServerErrorException("Storage error");
    }
  }

  @Override
  public void createUserAvatar(UUID id, byte[] content) throws HttpException {
    try {
      userService.createAvatar(id, content);
    } catch(StorageException e) {
      throw new ConflictException("Avatar already exists");
    }
  }
}
