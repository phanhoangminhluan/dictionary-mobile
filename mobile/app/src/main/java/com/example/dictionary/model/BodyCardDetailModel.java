package com.example.dictionary.model;

import java.util.ArrayList;

public class BodyCardDetailModel {
    private String messages, success;
    private Card body;

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

    public Card getBody() {
        return body;
    }

    public void setBody(Card body) {
        this.body = body;
    }
}
