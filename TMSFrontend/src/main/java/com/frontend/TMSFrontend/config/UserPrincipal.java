package com.frontend.TMSFrontend.config;

import com.frontend.TMSFrontend.model.Roles;
import com.frontend.TMSFrontend.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserPrincipal implements UserDetails {
    Logger LOGGER = LoggerFactory.getLogger(UserPrincipal.class);

    private User user;
    private List<Roles> authGroups;

    public UserPrincipal(User user, List<Roles> authGroups) {
        super();
        this.user = user;
        this.authGroups = authGroups;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authGroups==null)
            return Collections.emptySet();
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();

        authGroups.forEach(authGroup -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(authGroup.getRolename()));
            LOGGER.debug(authGroup.getRolename());
        });

//        grantedAuthorities.forEach(auth ->{
//            LOGGER.debug(auth.getAuthority());
//        });

        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
