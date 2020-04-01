package com.example.dictionary.model;

import java.util.ArrayList;

public class BodyFavorite {
    private String messages, success;
    private ArrayList<FavoriteWord> body;

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

    public ArrayList<FavoriteWord> getBody() {
        return body;
    }

    public void setBody(ArrayList<FavoriteWord> body) {
        this.body = body;
    }
}
