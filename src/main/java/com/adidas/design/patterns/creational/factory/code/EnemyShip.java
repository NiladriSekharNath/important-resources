package com.adidas.design.patterns.creational.factory.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class EnemyShip {

    private String enemyShipTypeName;

    private int damageAbility;

    public String getEnemyShipTypeName() {
        return this.enemyShipTypeName;
    }

    public void setEnemyShipTypeName(String enemyShipTypeName) {
        this.enemyShipTypeName = enemyShipTypeName;
    }

    public int getDamageAbility() {
        return this.damageAbility;
    }

    public void setDamageAbility(int damageAbility) {
        this.damageAbility = damageAbility;
    }

    public void followHeroShip() {
        log.info("Hero Ship is followed by : {}", enemyShipTypeName);
    }

    public void attackHeroShip() {
        log.info("Hero Ship is attacked by : {}, damage : {}", enemyShipTypeName, damageAbility);
    }

    public void displayEnemyShip() {
        log.info("Enemy Ship is on the screen type : {}", enemyShipTypeName);
    }

}
