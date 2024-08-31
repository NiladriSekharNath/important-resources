package com.adidas.design.patterns.structural.adapter.code;

public class EnemyRobotAdapter implements EnemyAdapter{

    private EnemyRobot enemyRobot;

    public EnemyRobotAdapter(EnemyRobot enemyRobot){
        this.enemyRobot = enemyRobot;
    }
    @Override
    public void fireWeapon() {
        enemyRobot.smashWithHands();
    }

    @Override
    public void driveForward() {
        enemyRobot.walkForward();
    }

    @Override
    public void assignDriver(String driverName) {
        enemyRobot.reactToHuman(driverName);
    }
}
