package com.iamscratches.TMS.model.roles;

import lombok.EqualsAndHashCode;

import javax.validation.constraints.Size;
import java.io.Serializable;

@EqualsAndHashCode
public class RolesPkId implements Serializable {
    @Size(min=1,max=15)
    private String username;
    private String rolename;

    public RolesPkId() {
    }

    public RolesPkId(String username, String rolename) {
        this.username = username;
        this.rolename = rolename;
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

    @Override
    public String toString() {
        return "RolesPkId{" +
                "username='" + username + '\'' +
                ", rolename='" + rolename + '\'' +
                '}';
    }
}
