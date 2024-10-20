package com.example.warhammer.repository.army;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import com.example.warhammer.model.Army;
import com.example.warhammer.repository.exception.EntityExistsException;
import com.example.warhammer.repository.exception.NonexistentEntityException;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArmyMemoryRepository implements ArmyRepository {

  private List<Army> armies;

  public ArmyMemoryRepository() {
    this.armies = new CopyOnWriteArrayList<>();
  }

  @Override
  public List<Army> all() {
    return this.armies;
  }

  @Override
  public Optional<Army> get(UUID id) {
    return this.armies
      .stream()
      .filter(army -> {
        return army.getId().equals(id);
      })
      .findFirst();
  }

  @Override
  public void add(Army entity) throws EntityExistsException {
    if (this.get(entity.getId()).isPresent()) {
      throw new EntityExistsException();
    } 
    this.armies.add(entity);
  }

  @Override
  public void delete(UUID id) throws NonexistentEntityException {
    Optional<Army> army = this.get(id);
    if (army.isEmpty()) {
      throw new NonexistentEntityException();
    }
    Army deleted = army.get();
    this.armies.remove(deleted);
  }

  @Override
  public void update(Army entity) throws NonexistentEntityException {
    Optional<Army> army = this.get(entity.getId());
    if (army.isEmpty()) {
      throw new NonexistentEntityException();
    }
    Army updated = army.get();
    int index = this.armies.indexOf(updated);
    this.armies.set(index, entity);
  }

}
