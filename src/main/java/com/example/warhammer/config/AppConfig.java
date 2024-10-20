package com.example.warhammer.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;

@Getter
@ApplicationScoped
public class AppConfig {
  private int initialUserRating;
  private Path avatarStoragePath; 

  private final String INITIAL_RATING_KEY = "INITIAL_USER_RATING";
  private final String AVATAR_STORAGE_PATH_KEY = "AVATARS_PATH";

  private String getStringWithDefault(Dotenv config, String key, String defaultValue) {
    String res = config.get(key, defaultValue);
    return res;
  }

  private int getIntWithDefault(Dotenv config, String key, int defaultValue) {
    try {
      String stringified = config.get(key);
      int res = Integer.parseInt(stringified);
      return res;
    } catch (Exception e) {
      return defaultValue;
    }
  }

  public AppConfig() {
    Dotenv config = Dotenv.load();
    int baseRating = getIntWithDefault(config, INITIAL_RATING_KEY, 10);
    String avatarPathString = getStringWithDefault(config, AVATAR_STORAGE_PATH_KEY, "/tmp");
    Path avatarStoragePath = Paths.get(avatarPathString);

    this.initialUserRating = baseRating;
    this.avatarStoragePath = avatarStoragePath;
  }
}
