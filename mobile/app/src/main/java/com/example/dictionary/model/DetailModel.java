package com.example.dictionary.model;

public class DetailModel {

    String messages;
    String success;
    WordModel body;

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

    public WordModel getBody() {
        return body;
    }

    public void setBody(WordModel body) {
        this.body = body;
    }
}
