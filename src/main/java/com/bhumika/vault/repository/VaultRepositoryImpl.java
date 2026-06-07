package com.bhumika.vault.repository;

import com.bhumika.vault.entity.VaultEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class VaultRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<VaultEntry> findExpiringBetween(LocalDate start, LocalDate end) {

        Query query = new Query();

        query.addCriteria(
                Criteria.where("expiryDate")
                        .gte(start)
                        .lte(end)
        );

        return mongoTemplate.find(query, VaultEntry.class);
    }
}