package com.adidas.design.patterns.behavioural.strategy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Bird extends Animal{

    public Bird(){
        super();
        flyingType = new CanFly();
    }

    public void makeNoise(){
        log.info("Bird chirps");
    }
}
