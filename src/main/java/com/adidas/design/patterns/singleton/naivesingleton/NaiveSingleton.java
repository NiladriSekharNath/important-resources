package com.adidas.design.patterns.singleton.naivesingleton;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * mainly to demonstrate the use of singleton classes creation in a  single Threaded Environment
 */

@Slf4j
public class NaiveSingleton {
    private static NaiveSingleton singleton;

    @Getter
    private String value;

    private NaiveSingleton(String value) {
        try{
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.value = value;
    }

    public static NaiveSingleton getSingletonInstance(String value){
        if(singleton == null){
            singleton = new NaiveSingleton(value);
        }

        return singleton;
    }

    public static void main(String args[]){
        NaiveSingleton naiveSingletonInstance1 = NaiveSingleton.getSingletonInstance("foo");
        NaiveSingleton naiveSingletonInstance2 = NaiveSingleton.getSingletonInstance("bar");
        if(naiveSingletonInstance1.getValue() != naiveSingletonInstance2.getValue())
            log.info("Two Instances created");
        else
            log.info("One Intance created");

    }
}
