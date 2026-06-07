package com.bhumika.vault.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "vault_entries")
@Data
@NoArgsConstructor
public class VaultEntry {

    @Id
    private String id;
    @NonNull
    private String serviceName;
    private String maskedKey;
    private String ownerEmail;
    private LocalDate expiryDate;



}
