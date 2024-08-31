package com.adidas.design.patterns.creational.prototype.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Sheep extends Animal {
    private int sheepSize;
    private int sheepWeight;

    public Sheep(int sheepSize, int sheepWeight) {
        this.sheepSize = sheepSize;
        this.sheepWeight = sheepWeight;
        log.info("Sheep is made");
    }

    @Override
    public Animal makeCopy() {
        log.info("Sheep is being made");

        Sheep sheepObject = null;

        try {
            sheepObject = (Sheep) super.clone();
        } catch (CloneNotSupportedException e) {

        }
        return sheepObject;

    }

    @Override
    public String toString() {
        return String.format("Dolly is my hero and my size : %s, weight is %s kg", this.sheepSize, this.sheepWeight);
    }

}
