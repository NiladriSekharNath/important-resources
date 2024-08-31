package com.adidas.design.patterns.structural.adapter.code;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class EnemyRobot {

    /**
     * this is called the adaptee class where an adaper makes this class work using the methods of
     * EnemyAdapter but point is or general practice is we would need to have methods that more or less make sense and could be used
     * which is similar kind of methods,
     *
     * as smashWithHands() below method is almost similar to the EnemyTank fireWeapon() method but point is
     *
     * we should not use it like methods that doesn't make sense should not be used with the adapter pattern
     */

    Random generator = new Random();

    public void smashWithHands(){
        int attackDamage = generator.nextInt(10) + 1;

        log.info("Enemy robot cause {} damage with its hands ", attackDamage);
    }

    public void walkForward(){
        int movement = generator.nextInt(5) + 1;

        log.info("Enemy Robot walks forward {} spaces ", movement);
    }

    public void reactToHuman(String driverName){
        log.info("Enemy Robot tramps on {}", driverName );
    }
}
