package com.example.dictionary.model;

import java.util.ArrayList;

public class BodyCardSetModel {
    private String messages;
    private ArrayList<CardSetModel> body;
    private String success;

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public ArrayList<CardSetModel> getBody() {
        return body;
    }

    public void setBody(ArrayList<CardSetModel> body) {
        this.body = body;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
