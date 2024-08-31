package com.adidas.design.patterns.structural.decorator.code.betterway;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PizzaMakerMain {

    public static void main(String args[]) {
        Pizza basicPizza = new Mozarella(new TomatoSauce((new PlainPizza())));

        log.info("Ingredients of basic Pizza:  {}", basicPizza.getDescription());

        log.info("Cost of basic Pizza : {}", basicPizza.getCost());
    }
}
