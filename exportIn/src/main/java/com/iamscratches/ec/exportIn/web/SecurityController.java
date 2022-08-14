package com.iamscratches.ec.exportIn.web;

import com.iamscratches.ec.exportIn.domain.User;
import com.iamscratches.ec.exportIn.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class SecurityController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping(path = "/signin")
    public Authentication login(@RequestBody @Valid LoginDto loginDto){
        return userService.signin(loginDto.getUsername(), loginDto.getPassword());
    }

    @PostMapping("/signup")
    public User signup(@RequestBody @Valid LoginDto loginDto){
        return userService.signup(loginDto.getUsername(), loginDto.getPassword(), loginDto.getFirstName(),
                loginDto.getLastName()).orElseThrow(()-> new RuntimeException("User already exists"));
    }

//    @GetMapping
}
