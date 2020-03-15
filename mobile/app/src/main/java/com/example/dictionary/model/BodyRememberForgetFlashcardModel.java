package com.example.dictionary.model;

public class BodyRememberForgetFlashcardModel {
    private String messages, success;
    private RememberForgetFlashcardModel body;

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

    public RememberForgetFlashcardModel getBody() {
        return body;
    }

    public void setBody(RememberForgetFlashcardModel body) {
        this.body = body;
    }
}
