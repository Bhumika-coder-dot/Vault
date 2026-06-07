package com.bhumika.vault.repository;

import com.bhumika.vault.entity.ConfigVaultAppEntity;
import com.bhumika.vault.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigVaultAppRepository extends MongoRepository<ConfigVaultAppEntity, String> {


}
