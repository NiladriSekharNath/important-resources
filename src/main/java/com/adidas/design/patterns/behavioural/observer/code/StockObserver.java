package com.adidas.design.patterns.behavioural.observer.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StockObserver implements Observer {

    private double ibmPrice;
    private double applePrice;
    private double googlePrice;

    private static int observerIDTracker = 0;

    private int observerId;
    private Subject stockGrabber;

    public StockObserver(Subject stockGrabber) {
        this.stockGrabber = stockGrabber;
        this.observerId = ++observerIDTracker;
        log.info("New Observer created id: {} ", this.observerId);
        this.stockGrabber.registerObserver(this);
    }

    @Override
    public void update(double ibmPrice, double applePrice, double googlePrice) {
        this.ibmPrice = ibmPrice;
        this.applePrice = applePrice;
        this.googlePrice = googlePrice;
        printThePrices();
    }

    private void printThePrices() {
        log.info("Current Stock Price fetched Observer Id : {}, for IBM : {}, Apple : {}, Google : {}", this.observerId, this.ibmPrice, this.applePrice, this.googlePrice);
    }
}
