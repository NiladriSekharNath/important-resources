package com.adidas.design.patterns.structural.facade.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FundsCheck {

    private double cashInAccount = 1000.00;

    public double getCashInAccount() {
        return cashInAccount;
    }

    public void withDrawCashInAccount(double cashToWithdraw) {
        if (haveEnoughMoney(cashToWithdraw)) {
            cashInAccount -= cashToWithdraw;
            log.info("Withdrawal Complete : Current Balance is {}" , getCashInAccount() );
        }
    }

    public void depositCashInAccount(double cashDeposited) {
        cashInAccount += cashDeposited;
    }

    public boolean haveEnoughMoney(double cashToWithdraw) {
        if (cashToWithdraw > getCashInAccount()) {
            log.info("Error: You don't have enought money");
            log.info("Current Balance : {}", getCashInAccount());

            return false;
        } else {
            return true;
        }
    }

}
