package com.example.dictionary.model;

import java.util.ArrayList;

public class WordDefinitionDetailModel {
    String definition;
    String partOfSpeech;
    ArrayList<String> examples;
    ArrayList<String> derivations;
    ArrayList<String> synonyms;

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public ArrayList<String> getExamples() {
        return examples;
    }

    public void setExamples(ArrayList<String> examples) {
        this.examples = examples;
    }

    public ArrayList<String> getDerivations() {
        return derivations;
    }

    public void setDerivations(ArrayList<String> derivations) {
        this.derivations = derivations;
    }

    public ArrayList<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(ArrayList<String> synonyms) {
        this.synonyms = synonyms;
    }
}
