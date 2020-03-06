package com.example.dictionary.model;

import java.util.ArrayList;

public class FlashcardModel {
    private String messages;
    private ArrayList<BodyGetDetailFlashcardModel> body;
    private String success;

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public ArrayList<BodyGetDetailFlashcardModel> getBody() {
        return body;
    }

    public void setBody(ArrayList<BodyGetDetailFlashcardModel> body) {
        this.body = body;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
