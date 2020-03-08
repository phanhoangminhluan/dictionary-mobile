package com.example.dictionary.model;

import java.util.ArrayList;

public class BodyCardModel {
    private String messages, success;
    private ArrayList<Card> body;

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

    public ArrayList<Card> getBody() {
        return body;
    }

    public void setBody(ArrayList<Card> body) {
        this.body = body;
    }
}
