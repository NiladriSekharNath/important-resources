package com.adidas.design.patterns.behavioural.state.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoCard implements AtmState {

    private AtmMachine atmMachine = null;
    public NoCard(AtmMachine atmMachine) {
        this.atmMachine = atmMachine;
    }

    /**
     * just like in the hasCard state this method is used to enter a new Card, so there we returned
     *
     * "we can't enter more than one card " so for the NoCard state when we called the insertCard method we can send in the next message
     *
     * as please enter pin
     *
     */
    @Override
    public void insertCard() {
        log.info("Please enter your PIN");
        this.atmMachine.setAtmState(this.atmMachine.getYesCardState());
    }

    @Override
    public void ejectCard() {
        log.info("please enter a card first");
    }

    @Override
    public void insertPin(int enteredPin) {
        log.info("Please enter a card first");
    }

    @Override
    public void requestCash(int cashToWithdraw) {
        log.info("Please enter a card first");
    }
}
