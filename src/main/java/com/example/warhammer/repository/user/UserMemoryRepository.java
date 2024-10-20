package com.example.warhammer.repository.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import com.example.warhammer.model.User;
import com.example.warhammer.repository.exception.EntityExistsException;
import com.example.warhammer.repository.exception.NonexistentEntityException;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserMemoryRepository implements UserRepository {

  private List<User> usersInMem;

  public UserMemoryRepository() {
    usersInMem = new CopyOnWriteArrayList<User>();
  }


  @Override
  public Optional<User> get(UUID id) {
    return this.usersInMem
      .stream()
      .filter(user -> {
        return user.getId().equals(id);
      })
      .findFirst();
  }

  @Override
  public List<User> all() {
    return this.usersInMem;
  }

  @Override
  public void add(User entity) throws EntityExistsException {
    if (this.get(entity.getId()).isPresent()) {
      throw new EntityExistsException();
    } 
    this.usersInMem.add(entity);
  }

  @Override
  public void update(User entity) throws NonexistentEntityException {
    Optional<User> user = this.get(entity.getId());
    if (user.isEmpty()) {
      throw new NonexistentEntityException();
    }
    User updated = user.get();
    int index = this.usersInMem.indexOf(updated);
    this.usersInMem.set(index, entity);
  }

  @Override
  public void delete(UUID id) throws NonexistentEntityException {
    Optional<User> user = this.get(id);
    if (user.isEmpty()) {
      throw new NonexistentEntityException();
    }
    User deleted = user.get();
    this.usersInMem.remove(deleted);
  }
}
