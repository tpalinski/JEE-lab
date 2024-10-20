package com.example.warhammer.storage.avatar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;
import java.util.UUID;

import com.example.warhammer.config.AppConfig;
import com.example.warhammer.storage.exception.StorageException;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@RequestScoped
public class LocalAvatarStorage implements AvatarStorage {

  private AppConfig config;

  @Inject
  public LocalAvatarStorage(AppConfig config) {
    this.config = config;
  }

  private final String FILE_EXTENSION = ".png";

  private Path resolveFilePath(UUID id) {
    String fileName = id.toString() + this.FILE_EXTENSION;
    return config.getAvatarStoragePath().resolve(fileName);
  }


  @Override
  public Optional<byte[]> get(UUID id) {
    Path filePath = this.resolveFilePath(id);
    if (!Files.exists(filePath)) {
      return Optional.empty();
    }
    try {
      byte[] content = Files.readAllBytes(filePath);
      return Optional.of(content);
    } catch (Exception e){
      return Optional.empty();
    }
  }

  @Override
  public void delete(UUID id) throws StorageException{
    Path filePath = this.resolveFilePath(id);
    try {
      Files.delete(filePath);
    } catch (IOException e) {
      throw new StorageException();
    }
  }
  @Override
  public void put(UUID id, byte[] content) throws StorageException {
    Path filePath = this.resolveFilePath(id);
    try {
      Files.write(filePath, content, StandardOpenOption.CREATE);
    } catch (IOException e) {
      throw new StorageException();
    }
  }
}
