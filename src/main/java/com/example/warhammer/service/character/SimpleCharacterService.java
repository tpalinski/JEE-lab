package com.example.warhammer.service.character;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.warhammer.model.Army;
import com.example.warhammer.model.Character;
import com.example.warhammer.model.User;
import com.example.warhammer.repository.army.ArmyRepository;
import com.example.warhammer.repository.character.CharacterRepository;
import com.example.warhammer.repository.exception.EntityExistsException;
import com.example.warhammer.repository.exception.NonexistentEntityException;
import com.example.warhammer.repository.user.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class SimpleCharacterService implements CharacterService {

  private CharacterRepository characterRepository;
  private UserRepository userRepository;
  private ArmyRepository armyRepository;

  @Inject
  public SimpleCharacterService(CharacterRepository characterRepository, UserRepository userRepository, ArmyRepository armyRepository) {
    this.characterRepository = characterRepository;
    this.armyRepository = armyRepository;
    this.userRepository = userRepository;
  }

  @Override
  public List<Character> getCharacters() {
    return this.characterRepository.all();
  }

  @Override
  public Optional<Character> getCharacter(UUID id) {
    return this.characterRepository.get(id);
  }

  @Override
  public void addCharacter(Character character) throws EntityExistsException, NonexistentEntityException {
    this.characterRepository.add(character);
    // TODO - move to Jakarta persistence
    // Handle user key integrity
    User user = this.userRepository.get(character.getUserId()).get();
    List<UUID> userChars = user.getCharacters();
    userChars.add(character.getId());
    user.setCharacters(userChars);
    this.userRepository.update(user);
    // Handle army key integrity
    Army army = this.armyRepository.get(character.getArmyId()).get();
    List<UUID> armyChars = army.getCharacters();
    armyChars.add(character.getId());
    army.setCharacters(armyChars);
    this.armyRepository.update(army);
  }

  @Override
  public void deleteCharacter(UUID id) throws NonexistentEntityException {
    // TODO - move to Jakarta persistence
    // Handle user key integrity
    Character character = this.characterRepository.get(id).get();
    User user = this.userRepository.get(character.getUserId()).get();
    List<UUID> userChars = user.getCharacters();
    userChars.removeIf(characterId -> characterId.equals(id));
    user.setCharacters(userChars);
    this.userRepository.update(user);
    // Handle army key integrity
    Army army = this.armyRepository.get(character.getArmyId()).get();
    List<UUID> armyChars = army.getCharacters();
    armyChars.removeIf(characterId -> characterId.equals(id));
    army.setCharacters(armyChars);
    this.armyRepository.update(army);

    // Actual logic
    this.characterRepository.delete(id);
  }

  @Override
  public void updateCharacter(Character character) throws NonexistentEntityException {
    // TODO - move to Jakarta persistence
    UUID id = character.getId();
    Character oldCharacter = this.characterRepository.get(id).get();
    if(!oldCharacter.getArmyId().equals(character.getArmyId())) {
      // Handle army FK integrity
      Army newArmy = this.armyRepository.get(character.getArmyId()).get();
      List<UUID> newArmyChars = newArmy.getCharacters();
      Army oldArmy = this.armyRepository.get(oldCharacter.getArmyId()).get();
      List<UUID> oldArmyChars = oldArmy.getCharacters();
      oldArmyChars.removeIf(characterId -> characterId.equals(id));
      oldArmy.setCharacters(oldArmyChars);
      this.armyRepository.update(oldArmy);
      newArmyChars.add(id);
      newArmy.setCharacters(newArmyChars);
      this.armyRepository.update(newArmy);
    }
    if(!oldCharacter.getUserId().equals(character.getUserId())) {
      User newUser = this.userRepository.get(character.getUserId()).get();
      List<UUID> newUserChars = newUser.getCharacters();
      User oldUser = this.userRepository.get(oldCharacter.getUserId()).get();
      List<UUID> oldUserChars = oldUser.getCharacters();
      oldUserChars.removeIf(characterId -> characterId.equals(id));
      oldUser.setCharacters(oldUserChars);
      this.userRepository.update(oldUser);
      newUserChars.add(id);
      newUser.setCharacters(newUserChars);
      this.userRepository.update(newUser);
    }

    // Actual update logic
    this.characterRepository.update(character);
  }
}
