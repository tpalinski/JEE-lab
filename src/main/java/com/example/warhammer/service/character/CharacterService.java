package com.example.warhammer.service.character;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.warhammer.model.Character;
import com.example.warhammer.repository.exception.EntityExistsException;
import com.example.warhammer.repository.exception.NonexistentEntityException;

public interface CharacterService {
  List<Character> getCharacters();

  Optional<Character> getCharacter(UUID id);

  void addCharacter(Character character) throws EntityExistsException, NonexistentEntityException;

  void updateCharacter(Character character) throws NonexistentEntityException;

  void deleteCharacter(UUID id) throws NonexistentEntityException;
}
