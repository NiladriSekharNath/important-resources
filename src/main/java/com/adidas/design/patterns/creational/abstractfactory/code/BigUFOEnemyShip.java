package com.adidas.design.patterns.creational.abstractfactory.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BigUFOEnemyShip extends EnemyShip{

    // We define the type of ship we want to create
    // by stating we want to use the factory that
    // makes enemy ships

    private EnemyShipFactory enemyShipFactory;

    // The enemy ship required parts list is sent to
    // this method. They state that the enemy ship
    // must have a weapon and engine assigned. That
    // object also states the specific parts needed
    // to make a Boss UFO versus a Regular UFO

    public BigUFOEnemyShip(EnemyShipFactory enemyShipFactory){
        this.enemyShipFactory = enemyShipFactory;
    }
    @Override
    public void makeShip() {
        log.info("Making enemy Ship {} " + getName());

        // The specific weapon & engine needed were passed in
        // shipFactory. We are assigning those specific part
        // objects to the UFOBossEnemyShip here

        weapon = enemyShipFactory.enemyShipWeapon();
        engine = enemyShipFactory.enemyShipEngine();
    }
}
