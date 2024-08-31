package com.adidas.design.patterns.structural.decorator.code.betterway;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TomatoSauce extends ToppingsDecorator{
    public TomatoSauce(Pizza tempPizza) {

        super(tempPizza);
        log.info("Adding tomato sauce ");

    }

    @Override
    public String getDescription() {
        return tempPizza.getDescription() + ", Tomato Sauce";
    }

    @Override
    public double getCost() {
        return tempPizza.getCost() + .35;
    }

}
