package com.adidas.design.patterns.creational.abstractfactory.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class EnemyShip {

    private String name;

    // Newly defined objects that represent weapon & engine
    // These can be changed easily by assigning new parts
    // in UFOEnemyShipFactory or UFOBossEnemyShipFactory

    protected ESWeapon weapon;

    protected ESEngine engine;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void makeShip();

    public void displayEnemyShip() {
        log.info("Enemy Ship :{} is active and ready", getName());
    }


    public void followHeroShip() {
        log.info("Enemy Ship :{}, following hero at engine: {}", getName(), engine.getEngineDetails());
    }

    public void enemyShipShoots() {
        log.info("Enemy Ship :{}, shoots hero ship and does damage: {}", getName(), weapon.getWeaponDetails());
    }

    // If any EnemyShip object is printed to screen this shows up

    @Override
    public String toString() {

        String infoOnShip = String.format("The %s has a top speed of %s and an attack power of %s", name, engine.toString(), weapon.toString());

        return infoOnShip;
    }

}
