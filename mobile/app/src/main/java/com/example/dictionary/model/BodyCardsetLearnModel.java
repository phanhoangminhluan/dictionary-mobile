package com.example.dictionary.model;

import java.util.ArrayList;

public class BodyCardsetLearnModel {
   private String messages, success;
   private CardsetLearnModel body;

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

    public CardsetLearnModel getBody() {
        return body;
    }

    public void setBody(CardsetLearnModel body) {
        this.body = body;
    }
}
