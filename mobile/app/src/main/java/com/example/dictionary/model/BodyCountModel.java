package com.example.dictionary.model;

public class BodyCountModel {
    private String messages, success;
    private CountModel body;

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

    public CountModel getBody() {
        return body;
    }

    public void setBody(CountModel body) {
        this.body = body;
    }
}
