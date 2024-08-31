package com.adidas.design.patterns.behavioural.state.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HasCard implements AtmState {


    /**
     * store the correct state of the machine,
     */
    private AtmMachine atmMachine ;
    public HasCard(AtmMachine atmMachine) {
        this.atmMachine = atmMachine;
    }

    @Override
    public void insertCard() {
        log.info("You can't enter more than one Card");
    }

    @Override
    public void ejectCard() {
        log.info("Card Ejected ");
        this.atmMachine.setAtmState(this.atmMachine.getNoCardState());
    }

    @Override
    public void insertPin(int enteredPin) {
        if(enteredPin == 1234){
            log.info("Correct Pin entered ");
            this.atmMachine.setCorrectPinEntered(true);
            this.atmMachine.setAtmState(this.atmMachine.getHasPin());
        }
        else{
            log.error("Wrong Pin");
            this.atmMachine.setCorrectPinEntered(false);
            log.info("Card Ejected");

            this.atmMachine.setAtmState(this.atmMachine.getNoCardState());
        }
    }

    @Override
    public void requestCash(int cashToWithdraw) {
        /**
         * we gave this error message here because we are in the HasCard stage we can withDraw cash only when we are in the
         * HasCorrectPin state (Has
         */
        log.error("Enter PIN first ");
    }
}
