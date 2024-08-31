package com.adidas.design.patterns.behavioural.observer.code;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class GetTheStock implements Runnable {

    private int startTime;
    private String stock;
    private double price;

    private Random random;

    private Subject stockGrabber;

    public GetTheStock(Subject stockGrabber, int newStartTime, String stock, double newPrice) {
        this.stockGrabber = stockGrabber;
        startTime = newStartTime;
        this.stock = stock;
        price = newPrice;
        random = new Random();
    }

    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }

            double randNum = random.nextInt(10);
            price = price + randNum;

            if (stock == "IBM") ((StockGrabber) stockGrabber).setIbmPrice(price);
            if (stock == "Apple") ((StockGrabber) stockGrabber).setApplePrice(price);
            if (stock == "Google") ((StockGrabber) stockGrabber).setGooglePrice(price);

            log.info("Current stock increased stock: {}, price: {}, randNum: {}", stock, price, randNum);

        }
    }
}
