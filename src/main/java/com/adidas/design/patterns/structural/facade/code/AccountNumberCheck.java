package com.adidas.design.patterns.structural.facade.code;

public class AccountNumberCheck {

    private int accountNumber = 12345678;

    private int getAccountNumber() { return accountNumber; }

    public boolean accountActive(int accountNumberToCheck){
        if(accountNumberToCheck == getAccountNumber())
            return true;
        else
            return false;
    }
}
