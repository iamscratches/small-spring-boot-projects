package com.frontend.TMSFrontend.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontend.TMSFrontend.model.Roles;
import com.frontend.TMSFrontend.model.User;
import com.frontend.TMSFrontend.service.ViewInfoService;
import com.frontend.TMSFrontend.util.ResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TMSUserDetailsService implements UserDetailsService {

    Logger LOGGER = LoggerFactory.getLogger(TMSUserDetailsService.class);

    @Autowired
    private final ViewInfoService service;

    public TMSUserDetailsService(ViewInfoService service) {
        super();
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ResponseMapper mapper = this.service.getUserInfo(username);
            if(mapper.getResponse().getResponseCode() == HttpStatus.FOUND)
                user = objectMapper.convertValue(mapper.getValue(), User.class);
            else
                throw new UsernameNotFoundException("cannot find username: " + username);
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.debug("Found user : " + user.toString());
        List<Roles> authGroups = null;
        if(user!= null)
             authGroups = user.getRoles();

        return new UserPrincipal(user, authGroups);
    }
}