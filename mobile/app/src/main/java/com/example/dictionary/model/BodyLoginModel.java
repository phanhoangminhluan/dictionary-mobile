package com.example.dictionary.model;

public class BodyLoginModel {
    private String messages,success;
    private LoginModel body;

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public LoginModel getBody() {
        return body;
    }

    public void setBody(LoginModel body) {
        this.body = body;
    }
}
