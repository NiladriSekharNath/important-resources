package com.adidas.design.patterns.behavioural.memento.code;

/**
 * the basic objects that is stored in different states
 */
public class Memento {

    private String article ;

    public Memento(String article) {
        this.article = article;
    }

    public String getSavedArticle(){
        return this.article;
    }
}
