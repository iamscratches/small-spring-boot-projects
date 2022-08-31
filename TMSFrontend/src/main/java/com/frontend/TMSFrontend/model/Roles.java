package com.frontend.TMSFrontend.model;


public class Roles {

    private String username;

    private String rolename;

    private String roledesc;

    public Roles() {
    }

    public Roles(String username, String rolename, String roledesc) {
        this.username = username;
        this.rolename = rolename;
        this.roledesc = roledesc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "username='" + username + '\'' +
                ", rolename='" + rolename + '\'' +
                ", roledesc='" + roledesc + '\'' +
                '}';
    }
}
