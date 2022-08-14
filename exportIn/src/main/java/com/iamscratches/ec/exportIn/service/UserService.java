package com.iamscratches.ec.exportIn.service;

import com.iamscratches.ec.exportIn.domain.Role;
import com.iamscratches.ec.exportIn.domain.User;
import com.iamscratches.ec.exportIn.repository.RoleRepository;
import com.iamscratches.ec.exportIn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    private AuthenticationManager authenticationManager;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager,
                       RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Authentication signin(String username, String password) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> signup(String username, String firstName, String password, String lastName){
        if(!userRepository.findByUsername(username).isPresent()){
            Optional<Role> role = roleRepository.findByRoleName("ROLE_CSR");
            return Optional.of(userRepository.save(
                    new User(username, passwordEncoder.encode(password), firstName, lastName, role.get())
            ));
        }
        return Optional.empty();
    }

 }