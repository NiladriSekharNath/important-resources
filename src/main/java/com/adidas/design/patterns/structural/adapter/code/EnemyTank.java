package com.adidas.design.patterns.structural.adapter.code;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class EnemyTank implements EnemyAdapter{

    Random generator = new Random();

    @Override
    public void fireWeapon() {
        int attackDamage = generator.nextInt(10) + 1;
        log.info("Enemy Tank Does {} damage", attackDamage);
    }

    @Override
    public void driveForward() {
        int movement = generator.nextInt(5) + 1;

        log.info("Enemy tank moves {} spaces", movement);
    }

    @Override
    public void assignDriver(String driverName) {
        log.info("{} is driving the tank", driverName);

    }
}
