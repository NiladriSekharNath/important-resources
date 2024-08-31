package com.adidas.design.patterns.structural.adapter.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestEnemyAdapter {

    public static void main(String args[]){
        EnemyTank rx7Tank = new EnemyTank();

        EnemyRobot fredTheRobot = new EnemyRobot();

        EnemyAdapter robotAdapter = new EnemyRobotAdapter(fredTheRobot);

        log.info("The robot ");

        fredTheRobot.walkForward();
        fredTheRobot.reactToHuman("Paul");
        fredTheRobot.smashWithHands();

        log.info("The enemy tank");

        rx7Tank.assignDriver("Frank");
        rx7Tank.driveForward();
        rx7Tank.fireWeapon();

        log.info("The Robot with Adapter");

        robotAdapter.assignDriver("Mark");
        robotAdapter.driveForward();
        robotAdapter.fireWeapon();


    }
}
