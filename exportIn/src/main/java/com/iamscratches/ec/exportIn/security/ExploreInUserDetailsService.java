package com.iamscratches.ec.exportIn.security;

import com.iamscratches.ec.exportIn.domain.Role;
import com.iamscratches.ec.exportIn.domain.User;
import com.iamscratches.ec.exportIn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static org.springframework.security.core.userdetails.User.withUsername;

@Component
public class ExploreInUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User with name %s does not exist", s)));
        String roles[] = new String[user.getRoles().size()];
        int i=0;
        for(Role role: user.getRoles()){
            roles[i++] = role.toString();
        }

        return withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}