package com.adidas.design.patterns.behavioural.mediator.code;

public class TestStockMediator {

    public static void main(String args[]) {
        StockMediator nyse = new StockMediator();

        GoldmanSachs broker = new GoldmanSachs(nyse);
        JTPoorman broker2 = new JTPoorman(nyse);

        broker.saleOffer("MSFT", 100);
        broker.saleOffer("GOOG", 50);

        broker2.buyOffer("GOOG", 50);
        broker2.saleOffer("NRG", 10);

        broker.buyOffer("NRG", 10);

        nyse.getStockOfferings();
        
        nyse.printColleagues();


   }
}
