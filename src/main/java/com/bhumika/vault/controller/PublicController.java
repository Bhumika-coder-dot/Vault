package com.bhumika.vault.controller;

import com.bhumika.vault.entity.User;
import com.bhumika.vault.service.UserDetailsServiceImpl;
import com.bhumika.vault.service.UserService;
import com.bhumika.vault.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {


    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;




    @PostMapping("signup")
    public void createUser(@RequestBody User user){
        userService.saveNewUser(user);
    }


    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody User user){
      try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
            userDetailsService.loadUserByUsername(user.getUserName());
          UserDetails userDetails=userDetailsService.loadUserByUsername(user.getUserName());
          String jwt=jwtUtil.generateToken(userDetails.getUsername());
          return new ResponseEntity<>(jwt, HttpStatus.OK);

      }
      catch(Exception e)
      {
          log.error("Exception occured while createAuthentivationToken",e);
          return new ResponseEntity<>("Incorrect username or password",HttpStatus.BAD_REQUEST);

      }
    }
}
