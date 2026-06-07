package com.bhumika.vault.repository;

import com.bhumika.vault.entity.VaultEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VaultRepository extends MongoRepository<VaultEntry, String> {
}
