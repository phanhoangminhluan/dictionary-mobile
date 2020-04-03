package com.example.dictionary.model;

public class BodyUserModel {
    private String message, status;
    private UserModel body;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserModel getBody() {
        return body;
    }

    public void setBody(UserModel body) {
        this.body = body;
    }
}
