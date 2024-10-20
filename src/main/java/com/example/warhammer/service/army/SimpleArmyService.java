package com.example.warhammer.service.army;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.warhammer.model.Army;
import com.example.warhammer.repository.army.ArmyRepository;
import com.example.warhammer.repository.character.CharacterRepository;
import com.example.warhammer.repository.exception.EntityExistsException;
import com.example.warhammer.repository.exception.NonexistentEntityException;
import com.example.warhammer.service.character.CharacterService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class SimpleArmyService implements ArmyService {

  private ArmyRepository armyRepository;
  private CharacterService characterService;

  @Inject
  public SimpleArmyService(ArmyRepository armyRepository, CharacterService characterService) {
    this.armyRepository = armyRepository;
    this.characterService = characterService;
  }

  @Override
  public List<Army> getArmies() {
    return this.armyRepository.all();
  }

  @Override
  public Optional<Army> getArmy(UUID id) {
    return this.armyRepository.get(id);
  }

  @Override
  public void addArmy(Army army) throws EntityExistsException {
    this.armyRepository.add(army);
  }

  @Override
  public void updateArmy(Army army) throws NonexistentEntityException {
    this.armyRepository.update(army);
  }

  @Override
  public void deleteArmy(UUID id) throws NonexistentEntityException {
    // TODO - move to Jakarta Persistence
    Army army = this.armyRepository.get(id).get();
    for (UUID characterId : army.getCharacters()) {
      this.characterService.deleteCharacter(characterId);
    }
    // Actual logic
    this.armyRepository.delete(id);
  }
}
