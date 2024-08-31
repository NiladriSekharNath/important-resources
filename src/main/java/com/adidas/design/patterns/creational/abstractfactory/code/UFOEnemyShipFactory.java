package com.adidas.design.patterns.creational.abstractfactory.code;

import lombok.extern.slf4j.Slf4j;

/**
 * // This factory uses the EnemyShipFactory interface
 * // to create very specific UFO Enemy Ship
 *
 * // This is where we define all of the parts the ship
 * // will use by defining the methods implemented
 * // being ESWeapon and ESEngine
 *
 * // The returned object specifies a specific weapon & engine
 */
@Slf4j
public class UFOEnemyShipFactory implements EnemyShipFactory{

    /**
     *
     * defines the weapon object to associated with the ship
     */
    @Override
    public ESWeapon enemyShipWeapon() {
        /**
         * specific to the regular UFO
         */
        return new ESUFOGun();
    }

    /**
     *
     * defines the engine object associated with the ship
     */

    @Override
    public ESEngine enemyShipEngine() {
        /**
         * specific to the regular UFO
         */
        return new ESUFOEngine();
    }
}
