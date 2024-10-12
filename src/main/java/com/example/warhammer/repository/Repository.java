package com.example.warhammer.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.warhammer.model.Model;
import com.example.warhammer.repository.exception.EntityExistsException;
import com.example.warhammer.repository.exception.NonexistentEntityException;

public interface Repository<T extends Model> {

  public Optional<T> get(UUID id);

  public List<T> all();

  public void add(T entity) throws EntityExistsException;

  public void update(T entity) throws NonexistentEntityException;

  public void delete(UUID id) throws NonexistentEntityException;
}
