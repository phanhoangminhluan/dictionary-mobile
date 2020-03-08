package com.example.dictionary.model;

public class BodyCreateCard {
    private String messages,success;
    private CardSetModel body;

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

    public CardSetModel getBody() {
        return body;
    }

    public void setBody(CardSetModel body) {
        this.body = body;
    }
}
