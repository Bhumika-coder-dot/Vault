package com.bhumika.vault.repository;

import com.bhumika.vault.entity.User;
import com.bhumika.vault.entity.VaultEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    void deleteByUserName(String userName);
    User findByUserName(String userName);
}
