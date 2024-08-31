package com.adidas.design.patterns.behavioural.mediator.code;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class StockMediator implements Mediator {

    private List<Colleague> colleagues;

    private List<StockOffer> stockBuyOffers;

    private List<StockOffer> stockSellOffers;

    private int colleagueCodes = 0;

    public StockMediator () {
        this.colleagues = new ArrayList<>();

        this.stockBuyOffers = new ArrayList<>();
        this.stockSellOffers = new ArrayList<>();
    }


    @Override
    public void addColleague(Colleague colleague) {
        this.colleagues.add(colleague);
        colleagueCodes++;
        colleague.setColleagueCode(colleagueCodes);
    }

    @Override
    public void saleOffer(String stock, int shares, int colleagueCode) {
        boolean stockSold = false;

        for(StockOffer offer : stockBuyOffers){
            if((offer.getStockSymbol() == stock) &&
                    (offer.getStockShares() == shares )){
                log.info("{} shares of {} sold to colleague code {}", shares, stock, offer.getColleagueCode());

                stockBuyOffers.remove(offer);
                stockSold = true;
            }

            if(stockSold) break;
        }

        if(!stockSold) {
            log.info("{} share of {} added to inventory", shares, stock);

            StockOffer newOffering = new StockOffer(shares, stock, colleagueCode);

            stockSellOffers.add(newOffering);
        }

    }

    @Override
    public void buyOffer(String stock, int shares, int colleagueCode) {
        boolean stockBought = false;

        for(StockOffer offer : stockSellOffers){
            if((offer.getStockSymbol() == stock) &&
                    (offer.getStockShares() == shares )){
                log.info("{} shares of {} bought by colleague code {}", shares, stock, offer.getColleagueCode());

                stockSellOffers.remove(offer);
                stockBought = true;
            }

            if(stockBought) break;
        }

        if(!stockBought) {
            log.info("{} share of {} added to inventory", shares, stock);

            StockOffer newOffering = new StockOffer(shares, stock, colleagueCode);

            stockBuyOffers.add(newOffering);
        }

    }

    public void getStockOfferings(){
        log.info("Stocks for Sale ");
        for(StockOffer offer : stockSellOffers){
            log.info("{} of {} ", offer.getStockShares(), offer.getStockSymbol());
        }

        log.info("Stocks Buy Offers ");
        for(StockOffer offer : stockBuyOffers){
            log.info("{} of {} ", offer.getStockShares(), offer.getStockSymbol());
        }
    }


    public void printColleagues(){
        log.info("All colleagues present : ");

        for(Colleague colleage : colleagues){
            log.info("Colleague : {}", colleage);
        }
    }

}
