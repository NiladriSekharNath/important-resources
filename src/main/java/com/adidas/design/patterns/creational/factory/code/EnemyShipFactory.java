package com.adidas.design.patterns.creational.factory.code;

public class EnemyShipFactory {
    public EnemyShip createEnemyShip(String type) {
        if ("UFO".equalsIgnoreCase(type))
            return new UFOEnemyShip();
        else if ("ROCKETSHIP".equalsIgnoreCase(type))
            return new RocketEnemyShip();
        else if ("BIGUFO".equalsIgnoreCase(type))
            return new BigUFOEnemyShip();
        return null;
    }
}
