package com.adidas.design.patterns.behavioural.mediator.code;

import lombok.ToString;

@ToString
public abstract class Colleague {

    private Mediator mediator ;

    private int colleagueCode;

    public Colleague(Mediator mediator){
        this.mediator = mediator;
        this.mediator.addColleague(this);
    }

    public void setColleagueCode(int colleagueCode){
        this.colleagueCode = colleagueCode;
    }

    public void saleOffer(String stock, int shares){
        mediator.saleOffer(stock, shares, this.colleagueCode);
    }

    public void buyOffer(String stock, int shares){
        mediator.buyOffer(stock, shares, this.colleagueCode);
    }


}
