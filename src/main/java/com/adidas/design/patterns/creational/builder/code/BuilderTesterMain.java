package com.adidas.design.patterns.creational.builder.code;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class BuilderTesterMain {
    private static Random random = new Random();

    public static void main(String args[]) {

        House newHouse1 = House.builder()
                .houseType("Prime Sea View Duplex")
                .rooms(2)
                .windows(1)
                .swimmingPool(true)
                .optionals("2 car garage")
                .build();

        log.info("Our new House created: {}", newHouse1);


        /**
         * other way of making the builder class is via the director class there are not many use cases found for this
         *
         */

        Meal meal = new MealDirector(new NonVegMealBuilder()).prepareMeal();

        log.info("New non Veg Meal : {}", meal);
    }

}
