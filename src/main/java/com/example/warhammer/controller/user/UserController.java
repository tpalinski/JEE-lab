package com.example.warhammer.controller.user;

import java.util.UUID;

import com.example.warhammer.controller.exception.HttpException;
import com.example.warhammer.dto.user.AddUserRequest;
import com.example.warhammer.dto.user.GetUserResponse;
import com.example.warhammer.dto.user.GetUsersResponse;
import com.example.warhammer.dto.user.UpdateUserRequest;

public interface UserController {
  GetUsersResponse getUsers() throws HttpException;

  GetUserResponse getUser(UUID id) throws HttpException;

  UUID addUser(AddUserRequest request) throws HttpException;

  void updateUser(UUID id, UpdateUserRequest request) throws HttpException;

  void deleteUser(UUID id) throws HttpException;

  byte[] getUserAvatar(UUID id) throws HttpException;

  void updateUserAvatar(UUID id, byte[] content) throws HttpException;

  void deleteUserAvatar(UUID id) throws HttpException;

  void createUserAvatar(UUID id, byte[] content) throws HttpException;
}
