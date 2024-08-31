package com.adidas.design.patterns.behavioural.observer.code;

public interface Subject {

    public void registerObserver(Observer observer);

    public void unregisterObserver(Observer observer);

    public void notifyObservers();
}
