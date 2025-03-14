package com.adidas.design.patterns.behavioural.mediator.code;

public interface Mediator {

    public void addColleague(Colleague colleague);

    public void saleOffer(String stock, int shares, int colleagueCode);


    public void buyOffer(String stock, int shares, int colleagueCode);


}
