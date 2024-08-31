package com.adidas.design.patterns.creational.abstractfactory.code;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class EnemyShipTestingClient {
    public static void main(String args[]){

        /**
         *  EnemyShipBuilding handles orders for new EnemyShips
         *  You send it a code using the orderTheShip method &
         *  it sends the order to the right factory for creation
         */

        Scanner scanner = new Scanner(System.in);
        log.info("Enter type of ship to create: ");
        String enemyShipType = scanner.nextLine();

        EnemyShipBuilding enemyShipBuilding = new UFOEnemyShipBuilding();

        enemyShipBuilding.orderTheShip(enemyShipType);
    }
}
