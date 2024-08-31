package com.adidas.design.patterns.creational.abstractfactory.code;

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
public class BigUFOEnemyShipFactory implements EnemyShipFactory {
    @Override
    public ESWeapon enemyShipWeapon() {
        // Specific to Boss UFO

        return new ESBigUFOWeapon();
    }

    @Override
    public ESEngine enemyShipEngine() {
        // Specific to Boss UFO

        return new ESBigUFOEngine();
    }
}
