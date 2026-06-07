package com.bhumika.vault.controller;
import com.bhumika.vault.api.response.WeatherResponse;
import com.bhumika.vault.entity.User;
import com.bhumika.vault.repository.UserRepository;
import com.bhumika.vault.service.UserService;
import com.bhumika.vault.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private WeatherService weatherService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
//    @GetMapping
//    public List<User> getAllUsers(){
//       return userService.getAll();
//    }

    @PutMapping
    public ResponseEntity<?> updateById(@RequestBody User user){
       Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
       String userName=authentication.getName();
        User userInDb=userService.findByUserName(userName);

            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveNewUser(userInDb);

return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @DeleteMapping
    public ResponseEntity<?> deleteById(@RequestBody User user){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
       userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        WeatherResponse weatherResponse = weatherService.getWeather("Pune");
       String greetings="";
       if(weatherResponse!=null){
           greetings=",  Weather feels like  "+weatherResponse.getMain().getTemp();
       }
        return new ResponseEntity<>(
                "Hi " + authentication.getName()+greetings,
                HttpStatus.OK
        );
    }





}
