package com.bhumika.vault.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {
    @Autowired
    private EmailService emailService;
    @Test
    void testSendEmail(){
        emailService.sendEMail("bhumikakolekar201@gmail.com","Testing java mail sender","Hii good afternoon");
    }

}
