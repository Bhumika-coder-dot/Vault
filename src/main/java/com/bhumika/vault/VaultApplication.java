
package com.bhumika.vault;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
@EnableScheduling
@SpringBootApplication
@EnableTransactionManagement
public class VaultApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context=SpringApplication.run(VaultApplication.class,args);
        ConfigurableEnvironment environment = context.getEnvironment();
        System.out.println(environment.getActiveProfiles()[0]);
//        SpringApplication.run(VaultApplication.class, args);
    }
    @Bean
    public PlatformTransactionManager add(MongoDatabaseFactory dbFactory){
        return new MongoTransactionManager(dbFactory);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
