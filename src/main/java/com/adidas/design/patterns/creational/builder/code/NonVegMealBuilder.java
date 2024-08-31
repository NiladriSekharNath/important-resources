package com.adidas.design.patterns.creational.builder.code;

public class NonVegMealBuilder extends MealBuilder{

    private Meal meal;
    public NonVegMealBuilder(){
        meal = new Meal();
    }
    @Override
    public void addBiryani() {
        this.meal.setBiriyani("Chicken");
    }

    @Override
    public void addBread() {
        this.meal.setBread("Naan");
    }

    @Override
    public void addColdDrink() {
        this.meal.setColdDrink("Coco Cola");
    }

    @Override
    public void addCurry() {
        this.meal.setCurry("Chicken Curry");
    }

    @Override
    public Meal build() {
        return meal;
    }
}
