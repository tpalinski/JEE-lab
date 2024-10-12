package com.example.warhammer.model;

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
  private Affiliation affiliation;

  public enum Affiliation {
    CHAOS,
    IMPERIUM,
    XENOS 
  }
}

