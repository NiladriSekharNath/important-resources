package com.adidas.design.patterns.structural.decorator.code.betterway;

import lombok.extern.slf4j.Slf4j;

/**
 * this is the base class which would be the PlainPizza and on this pizza we would add the toppings
 * so if we would want to add anything on the PlainPizza we would simply do something like as shown using the
 * ToppingsDecorator
 *
 */
@Slf4j
public class PlainPizza implements Pizza{
    public PlainPizza(){
        log.info("making dough");
    }
    @Override
    public String getDescription() {
        return "Thin Dough Pizza";
    }

    @Override
    public double getCost() {
        return 4.00;
    }
}
