package com.adidas.design.patterns.creational.builder.code;

public abstract class MealBuilder {
    public abstract void addBiryani();
    public  abstract  void  addBread();
    public abstract void  addColdDrink();
    public abstract void addCurry();
    public abstract Meal build();
}
