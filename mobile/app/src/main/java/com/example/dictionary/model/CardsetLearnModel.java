package com.example.dictionary.model;

import java.util.ArrayList;

public class CardsetLearnModel {
    private String cardSetSessionId, cardSetId;
   private ArrayList<StudiableCardModel> studiableCards;

    public String getCardSetSessionId() {
        return cardSetSessionId;
    }

    public void setCardSetSessionId(String cardSetSessionId) {
        this.cardSetSessionId = cardSetSessionId;
    }

    public String getCardSetId() {
        return cardSetId;
    }

    public void setCardSetId(String cardSetId) {
        this.cardSetId = cardSetId;
    }

    public ArrayList<StudiableCardModel> getStudiableCards() {
        return studiableCards;
    }

    public void setStudiableCards(ArrayList<StudiableCardModel> studiableCards) {
        this.studiableCards = studiableCards;
    }
}

