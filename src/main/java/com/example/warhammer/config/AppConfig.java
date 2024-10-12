package com.example.warhammer.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AppConfig {
  private final int initialUserRating = 10;
  private final Path avatarStoragePath = Paths.get("/home/tymek/projects/JEE-lab/data/avatars");
}
