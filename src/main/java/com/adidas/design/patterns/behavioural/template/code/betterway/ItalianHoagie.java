package com.adidas.design.patterns.behavioural.template.code.betterway;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ItalianHoagie extends Hoagie {

    String[] meatUsed = {"Salami", "Pepperoni", "Capicola Ham"};
    String[] cheeseUsed = {"Provolone"};
    String[] veggiesUsed = {"Lettuce", "Tomatoes", "Onions", "Sweet Peppers"};
    String[] condimentsUsed = {"Oil" , "Vinegar"};

    @Override
    void addMeat() {
        StringBuilder stringBuilder = new StringBuilder();

        for(String meat : meatUsed){
            stringBuilder.append(meat + " ");
        }

        log.info("Adding the meats {}", stringBuilder);
    }

    @Override
    void addCheese() {
        StringBuilder stringBuilder = new StringBuilder();

        for(String cheese : cheeseUsed){
            stringBuilder.append(cheese + " ");
        }

        log.info("Adding the cheeses: {}", stringBuilder);
    }

    @Override
    void addVegetables() {
        StringBuilder stringBuilder = new StringBuilder();

        for(String veggie : veggiesUsed){
            stringBuilder.append(veggie + " ");
        }

        log.info("Adding the veggies {}", stringBuilder);
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
