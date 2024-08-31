package com.adidas.design.patterns.behavioural.strategy.code;

public class CantFly implements Flys{
    @Override
    public String fly() {
        return "It Cant fly";
    }
}
