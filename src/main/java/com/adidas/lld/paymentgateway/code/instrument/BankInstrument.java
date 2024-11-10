package com.adidas.lld.paymentgateway.code.instrument;

public class BankInstrument extends Instrument {
  private String bankAccountNumber;
  private String ifscCode;

  /**
   * getters and setters
   */

  public String getBankAccountNumber() {
    return bankAccountNumber;
  }

  public void setBankAccountNumber(String bankAccountNumber) {
    this.bankAccountNumber = bankAccountNumber;
  }

  public String getIfscCode() {
    return ifscCode;
  }

  public void setIfscCode(String ifscCode) {
    this.ifscCode = ifscCode;
  }
}
