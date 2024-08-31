package com.adidas.design.patterns.behavioural.template.code.betterway;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VeggieHoagie extends Hoagie{

    String[] veggiesUsed = {"Lettuce", "Tomatoes", "Onions", "Sweet Peppers"};
    String[] condimentsUsed = {"Oil" , "Vinegar"};

    @Override
    boolean customerWantsMeat() {
        return false;
    }

    @Override
    boolean customerWantsCheese() {
        return false;
    }

    @Override
    void addMeat() { }

    @Override
    void addCheese() { }

    @Override
    void addVegetables() {
        StringBuilder stringBuilder = new StringBuilder();

        for(String veggie : veggiesUsed){
            stringBuilder.append(veggie + " ");
        }

        log.info("Adding the veggies: {}", stringBuilder);
    }

    @Override
    void addCondiments() {
        StringBuilder stringBuilder = new StringBuilder();

        for(String condiment : condimentsUsed){
            stringBuilder.append(condiment + " ");
        }

        log.info("Adding the condiments: {}", stringBuilder);
    }
}
