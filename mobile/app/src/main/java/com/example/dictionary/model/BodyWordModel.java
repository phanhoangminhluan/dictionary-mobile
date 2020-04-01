package com.example.dictionary.model;

import java.util.ArrayList;

public class BodyWordModel {
    private ArrayList<WordDefinitionDetailModel> definitionDetails;
    private String ukPhonetic, usPhonetic, word;

    public ArrayList<WordDefinitionDetailModel> getDefinitionDetails() {
        return definitionDetails;
    }

    public void setDefinitionDetails(ArrayList<WordDefinitionDetailModel> definitionDetails) {
        this.definitionDetails = definitionDetails;
    }

    public String getUkPhonetic() {
        return ukPhonetic;
    }

    public void setUkPhonetic(String ukPhonetic) {
        this.ukPhonetic = ukPhonetic;
    }

    public String getUsPhonetic() {
        return usPhonetic;
    }

    public void setUsPhonetic(String usPhonetic) {
        this.usPhonetic = usPhonetic;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
