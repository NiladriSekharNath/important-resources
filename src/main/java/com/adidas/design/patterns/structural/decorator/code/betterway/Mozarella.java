package com.adidas.design.patterns.structural.decorator.code.betterway;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Mozarella extends ToppingsDecorator{

    public Mozarella(Pizza tempPizza) {
        super(tempPizza);
        log.info("adding Mozzarella");
    }

    @Override
    public String getDescription() {
        return tempPizza.getDescription() + ", Mozzarella";
    }

    @Override
    public double getCost() {
        return tempPizza.getCost() + .50;
    }

}
