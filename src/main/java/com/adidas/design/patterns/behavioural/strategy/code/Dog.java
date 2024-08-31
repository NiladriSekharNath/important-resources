package com.adidas.design.patterns.behavioural.strategy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Dog extends Animal{
    public void makeNoise(){
        log.info("Dog makes Noise");
    }

    public void dogMethod(){

    }

    public Dog(){
        super();
        /**
         * this way it can be initialized, as we made the flyingType field
         * as protected, as child classes only can access the protected members
         *
         */
        flyingType = new CantFly();
        /**
         *  other way to set that we can do as it is private
         *
         *  setFlyingType(new CantFly());
         *
         */

    }

}
