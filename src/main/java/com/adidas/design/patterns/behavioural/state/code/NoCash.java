package com.adidas.design.patterns.behavioural.state.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoCash implements AtmState {

    private AtmMachine atmMachine ;
    public NoCash(AtmMachine atmMachine) {
        this.atmMachine = atmMachine;
    }

    @Override
    public void insertCard() {
        log.info(" we don't have money in the atm");
    }

    @Override
    public void ejectCard() {
        log.info(" WE don't have money in the atm, You did not enter any card");
    }

    @Override
    public void insertPin(int enteredPin) {
        log.info(" We don't have money in the atm ");
    }

    @Override
    public void requestCash(int cashToWithdraw) {
        log.info("We don't have money in the atm");
    }
}
