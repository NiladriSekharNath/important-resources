package com.adidas.design.patterns.behavioural.state.code;

public class AtmMachine {

    private AtmState hasCard = null;
    private AtmState noCard = null;
    private AtmState hasCorrectPin = null;

    private AtmState atmOutOfMoney = null;

    /**
     * this would hold the actual AtmState of the system out of the above pre-defined states
     */
    private AtmState atmState;

    private int cashInMachine = 2000;

    private boolean correctPinEntered = false;

    public AtmMachine() {
        this.hasCard = new HasCard(this);
        this.noCard = new NoCard(this);
        this.hasCorrectPin = new HasPin(this);
        this.atmOutOfMoney = new NoCash(this);

        this.atmState = this.noCard;

        if (this.cashInMachine < 0) {
            this.atmState = this.atmOutOfMoney;
        }
    }

    public void setAtmState(AtmState newState) {
        this.atmState = newState;
    }

    public void setCashInMachine(int newCashInMachine) {
        this.cashInMachine = newCashInMachine;
    }

    public void insertCard() {
        this.atmState.insertCard();
    }

    public void ejectCard() {
        this.atmState.ejectCard();
    }

    public void requestCash(int cashToWithdraw) {
        this.atmState.requestCash(cashToWithdraw);
    }


    public void insertPin(int enteredPin) {
        this.atmState.insertPin(enteredPin);
    }


    public AtmState getYesCardState() {
        return this.hasCard;
    }

    public AtmState getNoCardState() {
        return this.noCard;
    }

    public AtmState getHasPin() {
        return this.hasCorrectPin;
    }

    public AtmState getNoCashState() {
        return this.atmOutOfMoney;
    }

    public boolean isCorrectPinEntered() {
        return correctPinEntered;
    }

    public void setCorrectPinEntered(boolean correctPinEntered) {
        this.correctPinEntered = correctPinEntered;
    }

    public int getCashInMachine(){
        return this.cashInMachine;
    }

}
