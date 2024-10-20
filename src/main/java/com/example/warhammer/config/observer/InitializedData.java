package com.example.warhammer.config.observer;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import com.example.warhammer.model.Army;
import com.example.warhammer.model.Character;
import com.example.warhammer.model.User;
import com.example.warhammer.service.army.ArmyService;
import com.example.warhammer.service.character.CharacterService;
import com.example.warhammer.service.user.UserService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.context.control.RequestContextController;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import lombok.SneakyThrows;

@ApplicationScoped
public class InitializedData {
  private final UserService userService;
  private final CharacterService characterService;
  private final ArmyService armyService;
  private final RequestContextController requestContextController;

  @Inject
  public InitializedData(UserService userService, CharacterService characterService, ArmyService armyService, RequestContextController requestContextController) {
    this.userService = userService;
    this.characterService = characterService;
    this.armyService = armyService;
    this.requestContextController = requestContextController;
  }


  public void contextInitialized(@Observes @Initialized(ApplicationScoped.class) Object init) {
    this.init();
  }

  @SneakyThrows
  public void init() {
    this.requestContextController.activate();
    UUID WE_ID = UUID.fromString("b8ce198f-6227-4b71-ac0a-fb5728083455");
    UUID TAU_ID = UUID.fromString("3031bea7-f3fc-414d-a5d0-ff1f6d9f7c84");
    UUID CUSTARD_ID = UUID.fromString("e46a6b04-1774-4ad7-bae5-38856116f791");
    // Initialize armies
    Army worldEaters = Army.builder()
      .id(WE_ID)
      .affiliation(Army.Affiliation.CHAOS)
      .name("World Eaters")
      .description("Servants of the blood god")
      .characters(new CopyOnWriteArrayList<>())
      .build();
    Army tau = Army.builder()
      .id(TAU_ID)
      .affiliation(Army.Affiliation.XENOS)
      .name("T'au Empire")
      .description("Space commies")
      .characters(new CopyOnWriteArrayList<>())
      .build();
    Army custard = Army.builder()
      .id(CUSTARD_ID)
      .affiliation(Army.Affiliation.IMPERIUM)
      .name("Adeptus Custodes")
      .description("Shiny boys")
      .characters(new CopyOnWriteArrayList<>())
      .build();
    this.armyService.addArmy(worldEaters);
    this.armyService.addArmy(tau);
    this.armyService.addArmy(custard);
    System.out.println("Armies:");
    System.out.println(this.armyService.getArmies());


    // Initialize users
    UUID ADMIN_ID = UUID.fromString("ce4fd963-a12f-4e6d-957a-af04109e53a0");
    UUID KHARN_ID = UUID.fromString("5e816723-4825-478f-8fd5-ef251432195d");
    UUID JOE_ID = UUID.fromString("806ce8c2-5434-4519-a524-e81c1ea61497");

    User admin = User.builder()
      .id(ADMIN_ID)
      .login("admin")
      .name("admin")
      .rating(999)
      .characters(new CopyOnWriteArrayList<>())
      .build();
    User kharn = User.builder()
      .id(KHARN_ID)
      .login("khorneLovesMe8")
      .name("Kharn the Betrayer")
      .rating(888)
      .characters(new CopyOnWriteArrayList<>())
      .build();
    User joe = User.builder()
      .id(JOE_ID)
      .login("joeshmo2137")
      .name("Joe Schmo")
      .rating(10)
      .characters(new CopyOnWriteArrayList<>())
      .build();
    this.userService.addUser(admin);
    this.userService.addUser(kharn);
    this.userService.addUser(joe);
    System.out.println("Users");
    System.out.println(this.userService.getUsers());

    // Initialize characters
    Character angron = Character.builder()
      .id(UUID.fromString("0fa78adf-2674-4c4f-99cd-b9af4d5969f7"))
      .armyId(WE_ID)
      .userId(KHARN_ID)
      .points(380)
      .name("Angron")
      .buyDate(new Date())
      .build();
    Character moe = Character.builder()
      .id(UUID.fromString("e35e6a1a-9062-447e-85aa-aa3457576f4b"))
      .armyId(WE_ID)
      .userId(KHARN_ID)
      .points(20)
      .name("Master of executions")
      .buyDate(new Date())
      .build();
    Character shadowsun = Character.builder()
      .id(UUID.fromString("785550b3-95f1-4876-8295-39dfca837e67"))
      .armyId(TAU_ID)
      .userId(JOE_ID)
      .points(100)
      .name("Commander Shadowsun")
      .buyDate(new Date())
      .build();
    Character warden = Character.builder()
      .id(UUID.fromString("e3f21c18-ede8-4892-a62f-eef6c84b1898"))
      .armyId(CUSTARD_ID)
      .userId(JOE_ID)
      .points(110)
      .name("Custodian Wardens")
      .buyDate(new Date())
      .build();
    this.characterService.addCharacter(angron);
    this.characterService.addCharacter(moe);
    this.characterService.addCharacter(shadowsun);
    this.characterService.addCharacter(warden);
    System.out.println("Characters");
    System.out.println(this.characterService.getCharacters());
    System.out.println(this.armyService.getArmies());
    System.out.println(this.userService.getUsers());
    System.out.println("--- Deleting army ---");
    this.armyService.deleteArmy(TAU_ID);
    System.out.println(this.characterService.getCharacters());
    System.out.println(this.armyService.getArmies());
    System.out.println(this.userService.getUsers());
    System.out.println("--- Deleting user ---");
    this.userService.deleteUser(KHARN_ID);
    System.out.println(this.characterService.getCharacters());
    System.out.println(this.armyService.getArmies());
    System.out.println(this.userService.getUsers());
    System.out.println("--- Updating unit (owner + army) ---");
    Character wardenUpdated = Character.builder()
      .id(UUID.fromString("e3f21c18-ede8-4892-a62f-eef6c84b1898"))
      .armyId(WE_ID)
      .userId(ADMIN_ID)
      .points(110)
      .name("Custodian Wardens")
      .buyDate(new Date())
      .build();
    this.characterService.updateCharacter(wardenUpdated);
    System.out.println(this.characterService.getCharacters());
    System.out.println(this.armyService.getArmies());
    System.out.println(this.userService.getUsers());
    // Cleanup
    this.requestContextController.deactivate();
  }
}
