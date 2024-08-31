package com.adidas.design.patterns.creational.abstractfactory.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UFOEnemyShip extends EnemyShip {

    // We define the type of ship we want to create
    // by stating we want to use the factory that
    // makes enemy ships
    private EnemyShipFactory enemyShipFactory;

    // The enemy ship required parts list is sent to
    // this method. They state that the enemy ship
    // must have a weapon and engine assigned. That
    // object also states the specific parts needed
    // to make a regular UFO versus a Boss UFO

    public UFOEnemyShip(EnemyShipFactory enemyShipFactory) {
        this.enemyShipFactory = enemyShipFactory;
    }

    @Override
    public void makeShip() {
        log.info("Making enemy Ship " + getName());

        weapon = enemyShipFactory.enemyShipWeapon();
        engine = enemyShipFactory.enemyShipEngine();
    }

}
