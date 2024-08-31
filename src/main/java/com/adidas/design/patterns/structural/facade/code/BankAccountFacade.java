package com.adidas.design.patterns.structural.facade.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BankAccountFacade {
    private int accountNumber;
    private int securityCode;

    private AccountNumberCheck accountNumberChecker;
    private SecurityCodeCheck securityCodeChecker;
    private FundsCheck fundsCheck;

    private WelcomeToBank bankWelcome;

    public BankAccountFacade(int newAccountNumber, int newSecurityCode) {
        this.accountNumber = newAccountNumber;
        this.securityCode = newSecurityCode;

        bankWelcome = new WelcomeToBank();

        accountNumberChecker = new AccountNumberCheck();
        securityCodeChecker = new SecurityCodeCheck();
        fundsCheck = new FundsCheck();

    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public int getSecurityCode() {
        return this.securityCode;
    }

    public void withdrawCash(double cashToGet) {
        if (accountNumberChecker.accountActive(getAccountNumber()) &&
                securityCodeChecker.isSecurityCodeCorrect(getSecurityCode()) &&
                fundsCheck.haveEnoughMoney(cashToGet)) {
            log.info("Transaction Complete ");
        } else
            log.info("Transaction Incomplete ");
    }

    public void depositCash(double cashToDeposit) {
        if (accountNumberChecker.accountActive(getAccountNumber()) &&
                securityCodeChecker.isSecurityCodeCorrect(getSecurityCode())) {
            fundsCheck.depositCashInAccount(cashToDeposit);

            log.info("Transaction complete ");
        } else {
            log.info("Transaction failed ");
        }
    }

}