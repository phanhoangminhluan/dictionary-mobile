package com.example.dictionary.model;

public class StudiableCardModel {
    private String cardId, cardSetSessionId;
    private boolean remember;
    private int rememberCount, forgetCount;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardSetSessionId() {
        return cardSetSessionId;
    }

    public void setCardSetSessionId(String cardSetSessionId) {
        this.cardSetSessionId = cardSetSessionId;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public int getRememberCount() {
        return rememberCount;
    }

    public void setRememberCount(int rememberCount) {
        this.rememberCount = rememberCount;
    }

    public int getForgetCount() {
        return forgetCount;
    }

    public void setForgetCount(int forgetCount) {
        this.forgetCount = forgetCount;
    }
}
