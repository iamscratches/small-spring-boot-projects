package com.frontend.TMSFrontend.model;


import java.util.List;


public class User {
    private String username;

    private String password;

    private String fname;

    private String lname;

    private Long contactNo;

    private String address;

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
