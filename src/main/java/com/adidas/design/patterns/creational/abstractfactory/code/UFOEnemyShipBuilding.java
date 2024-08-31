package com.adidas.design.patterns.creational.abstractfactory.code;

/**
 * This is the only class that needs to change, if you
 * want to determine which enemy ships you want to
 * provide as an option to build
 */
public class UFOEnemyShipBuilding extends EnemyShipBuilding{

    private EnemyShipFactory enemyShipPartsFactory;

    @Override
    protected EnemyShip makeEnemyShip(String typeOfShip) {
        EnemyShip theEnemyShip = null;

        /**
         * If UFO was sent grab use the factory that knows
         * what types of weapons and engines a regular UFO
         * needs. The EnemyShip object is returned & given a name
         */

        if("UFO".equalsIgnoreCase(typeOfShip)) {
            enemyShipPartsFactory = new UFOEnemyShipFactory();
            theEnemyShip = new UFOEnemyShip(enemyShipPartsFactory);
            theEnemyShip.setName("UFO Enemy Ship");
        }
        /**
         * If UFO BOSS was sent grab use the factory that knows
         * what types of weapons and engines a Boss UFO
         * needs. The EnemyShip object is returned & given a name
         */

        else if("BIGUFO".equalsIgnoreCase(typeOfShip)) {
            enemyShipPartsFactory = new BigUFOEnemyShipFactory();
            theEnemyShip = new BigUFOEnemyShip(enemyShipPartsFactory);
            theEnemyShip.setName("UFO BigEnemyShip Ship");
        }
        return theEnemyShip;
    }
}
