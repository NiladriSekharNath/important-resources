package com.adidas.design.patterns.behavioural.state.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HasPin implements AtmState {

    private AtmMachine atmMachine = null;

    public HasPin(AtmMachine atmState){
        this.atmMachine = atmState;
    }

    @Override
    public void insertCard() {
        log.info("You can't enter more than one card");
    }

    @Override
    public void ejectCard() {
        log.info("Card Ejected");
        this.atmMachine.setAtmState(this.atmMachine.getNoCardState());
    }

    @Override
    public void insertPin(int enteredPin) {
        log.info("Already entered pin");
    }

    @Override
    public void requestCash(int cashToWithdraw) {
        if(cashToWithdraw > this.atmMachine.getCashInMachine()){
            log.error("Don't have that Cash");
            log.info("Card ejcted");
            this.atmMachine.setAtmState(this.atmMachine.getNoCardState());
        }

        else{
            log.info("{} is provided by the machine", cashToWithdraw);
            atmMachine.setCashInMachine(this.atmMachine.getCashInMachine() - cashToWithdraw);

            if(this.atmMachine.getCashInMachine() <= 0){
                this.atmMachine.setAtmState(this.atmMachine.getNoCashState());
            }
        }
    }
}
