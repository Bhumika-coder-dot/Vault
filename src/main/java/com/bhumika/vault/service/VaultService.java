package com.bhumika.vault.service;

import com.bhumika.vault.entity.User;
import com.bhumika.vault.entity.VaultEntry;
import com.bhumika.vault.repository.VaultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VaultService {
    @Autowired
    private VaultRepository repository;

    @Autowired
    private UserService userService;

@Transactional
    public void saveEntry(VaultEntry entry, String userName){

        try{
            User user=userService.findByUserName(userName);
            VaultEntry saved= repository.save(entry);
            user.getVaultEntries().add(saved);
            userService.saveUser(user);
        }
        catch(Exception e){

                throw new RuntimeException("An error occured while saving the entry",e);
        }

    }

    public void saveEntry(VaultEntry entry){

        repository.save(entry);
    }

    public List<VaultEntry> getAll() {
        return repository.findAll();
    }

    @Transactional
    public boolean deleteEntry(String id, String userName){

        boolean removed=false;
        try {
            User user=userService.findByUserName(userName);
             removed= user.getVaultEntries().removeIf(x -> x.getId().equals(id));
            if(removed){
                userService.saveUser(user);
                repository.deleteById(id);

            }
            return removed;
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("error occured",e);
        }

    }



    public Optional<VaultEntry> getById(String id){
        return repository.findById(id);
    }
    public VaultEntry updateById(String id, VaultEntry newEntry) {

        VaultEntry old = repository.findById(id).orElse(null);

        if (old != null) {
            old.setServiceName(newEntry.getServiceName());
            old.setMaskedKey(newEntry.getMaskedKey());
            old.setOwnerEmail(newEntry.getOwnerEmail());
            old.setExpiryDate(newEntry.getExpiryDate());

            repository.save(old);
        }

        return old;
    }

}
