package com.adidas.design.patterns.creational.prototype.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestCloning {

    public static void main(String args[]){
        CloneFactory animalMaker = new CloneFactory();

        Sheep sally = new Sheep(15, 23);
        Sheep clonedSheep = (Sheep) animalMaker.CloneFactory(sally);

        log.info("sally : {}", sally);
        log.info("clonedSheep : {}", clonedSheep);

        log.info("Sally hashcode : {}", System.identityHashCode(System.identityHashCode(sally)));
        log.info("Cloned Sleep hascode :{} ", System.identityHashCode(System.identityHashCode(clonedSheep)));
    }
}
