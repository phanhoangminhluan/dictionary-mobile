package com.example.dictionary.model;

import java.util.ArrayList;

public class BodyCardSetDetailModel {
    private String messages;
    private CardSetModel body;
    private String success;

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public CardSetModel getBody() {
        return body;
    }

    public void setBody(CardSetModel body) {
        this.body = body;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
