package com.example.dictionary.model;

import java.util.ArrayList;

import retrofit2.http.Body;

public class DetailModel {

    String messages;
    String success;
    BodyGetDetailText body;

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

    public BodyGetDetailText getBody() {
        return body;
    }

    public void setBody(BodyGetDetailText body) {
        this.body = body;
    }
}
