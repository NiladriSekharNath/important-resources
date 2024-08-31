package com.adidas.design.patterns.creational.factory.code;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class EnemyShipTesting {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        /***
         * Main purpose of the factory method here is in runTime
         * we can directly at runtime assign the type of ship based on the userInput
         *
         * So here we have a centralized repository of the enemyships with various types which we can just set during runtime
         *
         * THIS IS NOT THE BEST WAY AS SHOWN BELOW IN THE COMMENTS DOWN :
         *
         *
         *         EnemyShip enemyShip = null;
         *         log.info("Entire type of enemy ship : ");
         *
         *         String shipType = scanner.nextLine();
         *
         *         if ("UFO".equalsIgnoreCase(shipType)) {
         *             enemyShip = new UFOEnemyShip();
         *         } else if ("ROCKETSHIP".equalsIgnoreCase(shipType))
         *             enemyShip = new RocketEnemyShip();
         *
         * as if we have different types of ships where we keep adding the ifs here, in the client code or the main class instead we should
         * be handling the same in a different class segregated altogether outside
         *
         *
         *
         */

        EnemyShip enemyShip = null;
        log.info("Entire type of enemy ship : ");

        String shipType = scanner.nextLine();

        /*if ("UFO".equalsIgnoreCase(shipType)) {
            enemyShip = new UFOEnemyShip();
        } else if ("ROCKETSHIP".equalsIgnoreCase(shipType))
            enemyShip = new RocketEnemyShip();
        */

        EnemyShipFactory enemyShipFactory = new EnemyShipFactory();

        /*

        enemyShip = EnemyShipFactory.createEnemyShip(shipType);

        createEnemyShip method in the EnemyShipFactory method can be static method also so with the above declaration

        */

        enemyShip = enemyShipFactory.createEnemyShip(shipType);

        if(enemyShip != null)
            performActions(enemyShip);
        else
            log.info("Please add correct values ");
    }

    private static void performActions(EnemyShip enemyShip) {
        enemyShip.displayEnemyShip();
        enemyShip.followHeroShip();
        enemyShip.attackHeroShip();
    }
}
