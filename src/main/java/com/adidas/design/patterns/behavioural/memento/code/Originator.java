package com.adidas.design.patterns.behavioural.memento.code;

import lombok.extern.slf4j.Slf4j;

/**
 * Sets and Gets values from the currently targeted Memento. Creates new Memento and assigns current values to them
 *
 * Stores the current state.
 * Creates a memento object containing a snapshot of the current state.
 * Restores its state from a memento.
 */
@Slf4j
public class Originator {

    private String article;

    public void set(String newArticle) {
        log.info("From Originator : Current version of Article {}", newArticle);

        this.article = newArticle;
    }

    public Memento storeInMemento() {
        log.info("From Originator : Saving to Memento ");

        return new Memento(article);
    }

    public String restoreFromMemento(Memento memento) {
        this.article = memento.getSavedArticle();
        log.info("From Originator : Previous Article Saved in Mementor {}", article);
        return this.article;
    }
}
