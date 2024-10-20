package com.example.warhammer.service.army;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.warhammer.model.Army;
import com.example.warhammer.repository.exception.EntityExistsException;
import com.example.warhammer.repository.exception.NonexistentEntityException;

public interface ArmyService {
  List<Army> getArmies();

  Optional<Army> getArmy(UUID id);

  void addArmy(Army army) throws EntityExistsException;

  void updateArmy(Army army) throws NonexistentEntityException;

  void deleteArmy(UUID id) throws NonexistentEntityException;
}
