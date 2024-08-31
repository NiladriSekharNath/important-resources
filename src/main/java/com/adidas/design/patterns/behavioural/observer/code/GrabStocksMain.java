package com.adidas.design.patterns.behavioural.observer.code;

public class GrabStocksMain {

    public static void main(String args[]) {
        StockGrabber stockGrabber = new StockGrabber();

        StockObserver observer1 = new StockObserver(stockGrabber);


        stockGrabber.setIbmPrice(196.00);
        stockGrabber.setApplePrice(1923.00);
        stockGrabber.setGooglePrice(197.00);

        StockObserver observer2 = new StockObserver(stockGrabber);

        stockGrabber.setIbmPrice(195.00);
        stockGrabber.setApplePrice(1922.00);
        stockGrabber.setGooglePrice(1937.00);

        stockGrabber.unregisterObserver(observer1);

        stockGrabber.setIbmPrice(197.00);
        stockGrabber.setApplePrice(2922.00);
        stockGrabber.setGooglePrice(3937.00);

        Thread ibmThread = new Thread(new GetTheStock(stockGrabber, 2, "IBM", 197.00));
        Thread appleThread = new Thread(new GetTheStock(stockGrabber, 2, "Apple", 196));
        Thread googleThread = new Thread(new GetTheStock(stockGrabber, 2, "Google", 201));

        ibmThread.start();
        appleThread.start();
        googleThread.start();
    }

}
