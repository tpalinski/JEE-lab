package com.example.warhammer.service.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.warhammer.model.User;
import com.example.warhammer.repository.exception.EntityExistsException;
import com.example.warhammer.repository.exception.NonexistentEntityException;
import com.example.warhammer.storage.exception.StorageException;

public interface UserService {
  List<User> getUsers();

  Optional<User> getUser(UUID id);

  void addUser(User user) throws EntityExistsException;

  void updateUser(User user) throws NonexistentEntityException;

  void deleteUser(UUID id) throws NonexistentEntityException;

  void updateAvatar(UUID id, byte[] avatar) throws StorageException;

  void createAvatar(UUID id, byte[] avatar) throws StorageException;

  void deleteAvatar(UUID id) throws StorageException;

  Optional<byte[]> getAvatar(UUID id);
}

