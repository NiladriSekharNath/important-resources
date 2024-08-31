package com.adidas.design.patterns.behavioural.mediator.code;

public class StockOffer {

    private int stockShares = 0 ;

    private String stockSymbol = "";

    private int colleagueCode = 0;

    public StockOffer(int numberOfShares, String stock, int colleagueCode){

        this.stockShares = numberOfShares;
        this.stockSymbol = stock;
        this.colleagueCode = colleagueCode;

    }

    public int getStockShares() { return this.stockShares; }

    public String getStockSymbol() { return this.stockSymbol; }

    public int getColleagueCode() { return this.colleagueCode; }
}
