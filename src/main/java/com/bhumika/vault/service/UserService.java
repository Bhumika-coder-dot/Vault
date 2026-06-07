package com.bhumika.vault.service;

import com.bhumika.vault.entity.User;
import com.bhumika.vault.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository repository;

    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public boolean saveNewUser(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            repository.save(user);
            return true;
        } catch (Exception e) {
            log.error("Hahahaaaaaaaaaaaaaa");
            log.info("Hahahaaaaaaaaaaaaaa");
            log.warn("Hahahaaaaaaaaaaaaaa");
            log.debug("Hahahaaaaaaaaaaaaaa");
            log.trace("Hahahaaaaaaaaaaaaaa");
            return false;
        }
    }
    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        repository.save(user);
    }

    public void saveUser(User entry){

         repository.save(entry);
    }


    public List<User> getAll() {
        return repository.findAll();
    }

    public boolean deleteEntry(String id){
        repository.deleteById(id);
        return true;
    }
    public Optional<User> getById(String id){
        return repository.findById(id);
    }

public User findByUserName(String userName){
        return repository.findByUserName((userName));
}
}
