package com.wms.inwms.domain.user;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserCredentials {
    @Column(unique = true, name = "user_name")
    private String username;

    private String password;

    private String role;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRole() {
        return this.role;
    }

    public UserCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserCredentials() {}
}