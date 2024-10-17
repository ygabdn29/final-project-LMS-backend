package com.example.demo.model.dto;

public class LoginDTO {
    private String username;
    private String password;
    private String requestedRole;
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
    public String getRequestedRole() {
        return requestedRole;
    }
    public void setRequestedRole(String requestedRole) {
        this.requestedRole = requestedRole;
    }

    
}
