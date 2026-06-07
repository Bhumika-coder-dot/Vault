package com.bhumika.vault.controller;
import com.bhumika.vault.entity.User;
import com.bhumika.vault.entity.VaultEntry;
import com.bhumika.vault.service.UserService;
import com.bhumika.vault.service.VaultService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vault")
public class VaultController {
    @Autowired
    private VaultService service;

    @Autowired
    private UserService userService;

    @GetMapping("/test")

    public String test(){
        return "Vault API is working";
    }


    @PostMapping
    public ResponseEntity<VaultEntry> addVault(@RequestBody VaultEntry entry) {
       try {
           Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
           String userName=authentication.getName();

           User user=userService.findByUserName(userName);
           service.saveEntry(entry,userName);
           return new ResponseEntity<>(entry, HttpStatus.CREATED);
       }
       catch(Exception e){
           e.printStackTrace();
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }
    @GetMapping
    public ResponseEntity<?> getAllVaultEntriesOfUser() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();

        User user=userService.findByUserName(userName);
        List<VaultEntry> all=user.getVaultEntries();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(all,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable String id){

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        boolean removed=service.deleteEntry(id,userName);
        if(removed) {


            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<VaultEntry> getById(@PathVariable String id){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user = userService.findByUserName(userName);
        List<VaultEntry> collect = user.getVaultEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<VaultEntry> vaultEntry= service.getById(id);

            if(vaultEntry.isPresent()){
                return new ResponseEntity<>(vaultEntry.get(), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/id/{id}")
    public ResponseEntity<VaultEntry> updateById(@PathVariable String id,
                                 @RequestBody VaultEntry newEntry) {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();


        User user = userService.findByUserName(userName);
        List<VaultEntry> collect = user.getVaultEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());

        if (!collect.isEmpty()) {
            Optional<VaultEntry> vaultEntry = service.getById(id);

            if (vaultEntry.isPresent()) {

                VaultEntry old = vaultEntry.get();

                old.setServiceName(
                        newEntry.getServiceName() != null &&
                                !newEntry.getServiceName().isEmpty()
                                ? newEntry.getServiceName()
                                : old.getServiceName()
                );

                old.setMaskedKey(
                        newEntry.getMaskedKey() != null &&
                                !newEntry.getMaskedKey().isEmpty()
                                ? newEntry.getMaskedKey()
                                : old.getMaskedKey()
                );

                old.setOwnerEmail(
                        newEntry.getOwnerEmail() != null &&
                                !newEntry.getOwnerEmail().isEmpty()
                                ? newEntry.getOwnerEmail()
                                : old.getOwnerEmail()
                );

                old.setExpiryDate(
                        newEntry.getExpiryDate() != null
                                ? newEntry.getExpiryDate()
                                : old.getExpiryDate()
                );

                service.saveEntry(old);

                return new ResponseEntity<>(old, HttpStatus.OK);
            }



        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
