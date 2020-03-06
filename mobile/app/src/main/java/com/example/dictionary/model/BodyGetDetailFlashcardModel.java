package com.example.dictionary.model;

import java.util.ArrayList;
import java.util.Date;

public class BodyGetDetailFlashcardModel {
    String id, name, username, cardSetSessionId;
    ArrayList<CardsDetailModel> cards;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCardSetSessionId() {
        return cardSetSessionId;
    }

    public void setCardSetSessionId(String cardSetSessionId) {
        this.cardSetSessionId = cardSetSessionId;
    }



    public ArrayList<CardsDetailModel> getCards() {
        return cards;
    }

    public void setCards(ArrayList<CardsDetailModel> cards) {
        this.cards = cards;
    }
}
