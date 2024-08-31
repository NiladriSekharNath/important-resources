package com.adidas.design.patterns.creational.prototype.code;

public class CloneFactory {

    public Animal CloneFactory(Animal animalSample){
        return animalSample.makeCopy();
    }
}
