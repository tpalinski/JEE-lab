package com.example.warhammer.repository.character;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import com.example.warhammer.model.Character;
import com.example.warhammer.repository.exception.EntityExistsException;
import com.example.warhammer.repository.exception.NonexistentEntityException;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CharacterMemoryRepository implements CharacterRepository {

  private List<Character> characters;

  public CharacterMemoryRepository() {
    this.characters = new CopyOnWriteArrayList<>();
  }

  @Override
  public List<Character> all() {
    return this.characters;
  }

  @Override
  public Optional<Character> get(UUID id) {
    return this.characters
      .stream()
      .filter(character -> {
        return character.getId().equals(id);
      })
      .findFirst();
  }

  @Override
  public void add(Character entity) throws EntityExistsException {
    if (this.get(entity.getId()).isPresent()) {
      throw new EntityExistsException();
    } 
    this.characters.add(entity);
  }

  @Override
  public void update(Character entity) throws NonexistentEntityException {
    Optional<Character> character = this.get(entity.getId());
    if (character.isEmpty()) {
      throw new NonexistentEntityException();
    }
    Character updated = character.get();
    int index = this.characters.indexOf(updated);
    this.characters.set(index, entity);
    
  }

  @Override
  public void delete(UUID id) throws NonexistentEntityException {
    Optional<Character> character = this.get(id);
    if (character.isEmpty()) {
      throw new NonexistentEntityException();
    }
    Character deleted = character.get();
    this.characters.remove(deleted);
    
  }
}
