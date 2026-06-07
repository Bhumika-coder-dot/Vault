package com.bhumika.vault.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Data
@Document(collection="config-vault-app")
@NoArgsConstructor
@Component
public class ConfigVaultAppEntity {
    private String key;
    private String value;
}
