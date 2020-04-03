package com.example.dictionary.model;

public class UserModel {
    private String username, email;

    public String getUsername() {
        return username;
    }

    public UserModel(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
