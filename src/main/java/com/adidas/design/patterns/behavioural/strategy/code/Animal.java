package com.adidas.design.patterns.behavioural.strategy.code;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Strategy Design Patterns
 */
@Slf4j
@ToString
public class Animal {

    public Animal() {
    }

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private double height;

    @Getter
    @Setter
    private String weight;

    /**
     * This is also called composition which is we are not inheriting
     * an ability through inheritance, but we are using different strategies(objects)
     * to enable a feature like let's say for a car we are using the tyre object
     * that makes the car move where different types of tyres are different strategies.
     *
     * Also, it can be changed during runtime since it's an object
     *
     * by that we mean, that when we try to change the field at runtime like for example use-cases:
     * for orders app the payments types(Cash, UPI) we can change also we can change the strategy we use to move from point A to point B
     * in a GPS app, like Bus, Car, Etc
     */
    protected Flys flyingType;

    public void makeNoise(){
        log.info("Animal make noise");
    }

    public String getFlyingType(){
        return this.flyingType.fly();
    }

    public void setFlyingType(Flys flyingType){
        this.flyingType = flyingType;
    }

    

}
