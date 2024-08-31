package com.adidas.design.patterns.structural.facade.code;

public class SecurityCodeCheck {

    private int securityNumber = 1234;

    private int getSecurityNumber() {
        return securityNumber;
    }

    public boolean isSecurityCodeCorrect(int securityNumberToCheck) {
        if (securityNumberToCheck == getSecurityNumber())
            return true;
        else
            return false;
    }


}
