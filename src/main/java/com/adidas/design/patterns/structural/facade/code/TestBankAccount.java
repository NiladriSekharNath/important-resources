package com.adidas.design.patterns.structural.facade.code;

public class TestBankAccount {

    public static void main(String args[]) {
        BankAccountFacade accessingBank = new BankAccountFacade(12345678, 1234);

        accessingBank.withdrawCash(50.00);
        accessingBank.depositCash(900.00);
    }
}
