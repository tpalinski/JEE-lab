package com.example.warhammer.storage;

import java.util.Optional;

import com.example.warhammer.storage.exception.StorageException;

// Interface representing blob storage
public interface Storage<T> {
  Optional<byte[]> get(T id) throws StorageException;
  void put(T id, byte[] content) throws StorageException;
  void delete(T id) throws StorageException;
}
