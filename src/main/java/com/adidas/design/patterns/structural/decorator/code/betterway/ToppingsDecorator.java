package com.adidas.design.patterns.structural.decorator.code.betterway;

public abstract class ToppingsDecorator implements Pizza {
    protected Pizza tempPizza;

    public ToppingsDecorator(Pizza tempPizza){
        this.tempPizza = tempPizza;

    }

    @Override
    public String getDescription() {
        return tempPizza.getDescription();
    }

    @Override
    public double getCost() {
        return tempPizza.getCost();
    }
}
