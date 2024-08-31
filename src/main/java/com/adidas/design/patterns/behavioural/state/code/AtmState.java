package com.adidas.design.patterns.behavioural.state.code;

/**
 * here we are listing all the ways the user would be able to use the ATM
 *
 * now for these become all the states we are supporting for our ATM Machine
 */
public interface AtmState {

    void insertCard();

    void ejectCard();

    void insertPin(int enteredPin);

    void requestCash(int cashToWithdraw);
}
