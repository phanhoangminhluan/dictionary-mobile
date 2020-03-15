package com.example.dictionary.model;

public class RememberForgetFlashcardModel {
    private String cardId,cardSetId, cardSetSessionId,term, definition, username;
    private boolean remember;
    private Integer rememberCount, forgetCount;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardSetId() {
        return cardSetId;
    }

    public void setCardSetId(String cardSetId) {
        this.cardSetId = cardSetId;
    }

    public String getCardSetSessionId() {
        return cardSetSessionId;
    }

    public void setCardSetSessionId(String cardSetSessionId) {
        this.cardSetSessionId = cardSetSessionId;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public Integer getRememberCount() {
        return rememberCount;
    }

    public void setRememberCount(Integer rememberCount) {
        this.rememberCount = rememberCount;
    }

    public Integer getForgetCount() {
        return forgetCount;
    }

    public void setForgetCount(Integer forgetCount) {
        this.forgetCount = forgetCount;
    }
}

