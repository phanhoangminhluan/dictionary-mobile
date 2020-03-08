package com.example.dictionary.model;

import java.util.ArrayList;

public class CreateCardSetModel {
    private String name;
    private ArrayList<Card> cards;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
}


