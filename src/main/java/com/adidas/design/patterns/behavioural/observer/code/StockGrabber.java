package com.adidas.design.patterns.behavioural.observer.code;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class StockGrabber implements Subject {

    private List<Observer> observers;

    private double ibmPrice;
    private double applePrice;
    private double googlePrice;

    public StockGrabber() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        int index = observers.indexOf(observer);
        log.info("Observer removed {}", (index + 1) );
        observers.remove(index);
    }

    @Override
    public void notifyObservers() {
        for (Observer eachObserver : observers) {
            eachObserver.update(ibmPrice, applePrice, googlePrice);
        }
    }

    public void setIbmPrice(double newIbmPrice) {
        this.ibmPrice = newIbmPrice;
        notifyObservers();
    }

    public void setApplePrice(double newApplePrice) {
        this.applePrice = newApplePrice;
        notifyObservers();
    }

    public void setGooglePrice(double newGooglePrice) {
        this.googlePrice = newGooglePrice;
        notifyObservers();
    }
}
