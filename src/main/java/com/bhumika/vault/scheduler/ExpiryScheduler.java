package com.bhumika.vault.scheduler;

import com.bhumika.vault.entity.VaultEntry;
import com.bhumika.vault.repository.VaultRepository;
import com.bhumika.vault.repository.VaultRepositoryImpl;
import com.bhumika.vault.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ExpiryScheduler {

    @Autowired
    private VaultRepository vaultRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private VaultRepositoryImpl vaultRepositoryImpl;

    //@Scheduled(fixedRate =10000)
    //Runs every day at 9:00 AM

 @Scheduled(cron = "0 0 9 * * ?")

    public void checkExpiry() {

        LocalDate today = LocalDate.now();
        LocalDate limit = today.plusDays(7);


        List<VaultEntry> entries =
                vaultRepositoryImpl.findExpiringBetween(today, limit);


     for (VaultEntry entry : entries) {

         long daysLeft = java.time.temporal.ChronoUnit.DAYS.between(
                 today,
                 entry.getExpiryDate()
         );

         if (daysLeft == 7 || daysLeft == 3 || daysLeft == 1) {

             String subject = "⚠ API Credential Expiry Alert";

             String body = "Hello,\n\n"
                     + "Your API credential for service: "
                     + entry.getServiceName()
                     + "\nwill expire in "
                     + daysLeft
                     + " day(s).\n\n"
                     + "Expiry Date: "
                     + entry.getExpiryDate()
                     + "\n\nPlease renew it before expiry.\n\n"
                     + "Thanks,\nVault System";

             emailService.sendEMail(
                     entry.getOwnerEmail(),
                     subject,
                     body
             );
         }
     }
    }

}