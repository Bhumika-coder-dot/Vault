package com.bhumika.vault.cache;

import com.bhumika.vault.entity.ConfigVaultAppEntity;
import com.bhumika.vault.repository.ConfigVaultAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }


    public Map<String,String> appCache = new HashMap<>();
    @Autowired
    private ConfigVaultAppRepository configVaultAppRepository;

    @PostConstruct
    public void init() {
        List<ConfigVaultAppEntity> all = configVaultAppRepository.findAll();
        for (ConfigVaultAppEntity configVaultAppEntity : all) {
            appCache.put(configVaultAppEntity.getKey(), configVaultAppEntity.getValue());
        }
    }


}
