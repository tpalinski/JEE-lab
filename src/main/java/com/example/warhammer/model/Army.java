package com.example.warhammer.model;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@ToString
public class Army extends Model {
  private String name;
  private String description;
  private List<UUID> characters;
  private Affiliation affiliation;

  public enum Affiliation {
    CHAOS,
    IMPERIUM,
    XENOS 
  }
}

