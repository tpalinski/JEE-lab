package com.example.warhammer.service.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.warhammer.model.User;
import com.example.warhammer.repository.character.CharacterRepository;
import com.example.warhammer.repository.exception.EntityExistsException;
import com.example.warhammer.repository.exception.NonexistentEntityException;
import com.example.warhammer.repository.user.UserRepository;
import com.example.warhammer.service.character.CharacterService;
import com.example.warhammer.storage.avatar.AvatarStorage;
import com.example.warhammer.storage.exception.StorageException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class SimpleUserService implements UserService {

  private UserRepository repository;
  private AvatarStorage avatarStorage;
  private CharacterService characterService;

  @Inject
  public SimpleUserService(UserRepository repository, AvatarStorage storage, CharacterService characterService) {
    this.repository = repository;
    this.avatarStorage = storage;
    this.characterService = characterService;
  }

  @Override
  public void addUser(User user) throws EntityExistsException {
     this.repository.add(user);
  }

  @Override
  public void updateUser(User user) throws NonexistentEntityException {
    this.repository.update(user);
  }

  @Override
  public void deleteUser(UUID id) throws NonexistentEntityException {
    // TODO - move to Jakarta Persistence
    User user = this.repository.get(id).get();
    for (UUID characterId : user.getCharacters()) {
      this.characterService.deleteCharacter(characterId);
    }
    // Actual logic
    this.repository.delete(id);
  }

  @Override
  public List<User> getUsers() {
    return this.repository.all();
  }

  @Override
  public Optional<User> getUser(UUID id) {
    return this.repository.get(id);
  }

  @Override
  public Optional<byte[]> getAvatar(UUID id) {
    try {
      return this.avatarStorage.get(id);
    } catch (StorageException e) {
      return Optional.empty();
    }
  }

  @Override
  public void deleteAvatar(UUID id) throws StorageException {
    this.avatarStorage.delete(id);
  }

  @Override
  public void updateAvatar(UUID id, byte[] avatar) throws StorageException {
    this.avatarStorage.put(id, avatar);
  }

  @Override
  public void createAvatar(UUID id, byte[] avatar) throws StorageException {
    if(this.getAvatar(id).isPresent()) {
      throw new StorageException();
    }  
    this.avatarStorage.put(id, avatar);
  }
}
