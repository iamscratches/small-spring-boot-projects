package com.example.iamscratches.guestapp.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class LandonUserPrincipal implements UserDetails {
    Logger LOGGER = LoggerFactory.getLogger(LandonUserPrincipal.class);

    private User user;
    private List<AuthGroup> authGroups;

    public LandonUserPrincipal(User user, List<AuthGroup> authGroups) {
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
            grantedAuthorities.add(new SimpleGrantedAuthority(authGroup.getAuthGroup()));
            LOGGER.error(authGroup.getAuthGroup());
        });

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
