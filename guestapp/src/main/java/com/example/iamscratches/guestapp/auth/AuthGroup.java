package com.example.iamscratches.guestapp.auth;

import javax.persistence.*;

@Entity
@Table(name = "AUTH_USER_GROUP")
public class AuthGroup {

    @Id
    @Column(name = "AUTH_USER_GROUP_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String username;
    @Column
    private String authGroup;

    public AuthGroup() {
    }

    public AuthGroup(Long id, String username, String authGroup) {
        this.id = id;
        this.username = username;
        this.authGroup = authGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthGroup() {
        return authGroup;
    }

    public void setAuthGroup(String authGroup) {
        this.authGroup = authGroup;
    }
}
