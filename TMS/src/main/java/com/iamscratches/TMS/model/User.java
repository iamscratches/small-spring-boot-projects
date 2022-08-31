package com.iamscratches.TMS.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table
public class User {
    @Id
    @Column(name="USERNAME")
    @Size(min=1,max=15)
    @NotNull
    private String username;

    @Column
    @NotNull
    private String password;

    @Column
    @NotNull
    private String fname;

    @Column
    @NotNull
    private String lname;

    @Column
    private Long contactNo;

    @Column
    private String address;

    @OneToMany
    @JoinColumn(name="USERNAME", nullable=false, insertable = false, updatable = false)
    private List<Roles> roles;

    public User() {
    }

    public User(String username, String password, String fname, String lname, Long contactNo, String address, List<Roles> roles) {
        this.username = username;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.contactNo = contactNo;
        this.address = address;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Long getContactNo() {
        return contactNo;
    }

    public void setContactNo(Long contactNo) {
        this.contactNo = contactNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", contactNo=" + contactNo +
                ", address='" + address + '\'' +
                ", roles=" + roles +
                '}';
    }
}
