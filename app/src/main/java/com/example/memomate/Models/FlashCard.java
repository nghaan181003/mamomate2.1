package com.example.memomate.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class FlashCard implements Serializable {
    private String term;
    private String definition;

    public FlashCard() {
    }

    public FlashCard(String term, String definition) {
        this.term = term;
        this.definition = definition;
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

}
