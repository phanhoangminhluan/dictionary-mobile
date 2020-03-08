package com.example.dictionary.model;

import java.util.ArrayList;

public class WordModel {
    String word;
    ArrayList<WordDefinitionDetailModel> definitionDetails;
    ArrayList<String> syllableList;
    String pronunciation;
    Integer frequency;


    public ArrayList<WordDefinitionDetailModel> getDefinitionDetails() {
        return definitionDetails;
    }

    public void setDefinitionDetails(ArrayList<WordDefinitionDetailModel> definitionDetails) {
        this.definitionDetails = definitionDetails;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public ArrayList<String> getSyllableList() {
        return syllableList;
    }

    public void setSyllableList(ArrayList<String> syllableList) {
        this.syllableList = syllableList;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }
}
