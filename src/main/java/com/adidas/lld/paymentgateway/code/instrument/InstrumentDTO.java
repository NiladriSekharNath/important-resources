package com.adidas.lld.paymentgateway.code.instrument;

public class InstrumentDTO {
  private String instrumentId;
  private String ifsc;
  private String cvv;

  private String bankAccountNumber;
  private String cardNumber;
  private InstrumentType instrumentType;
  private String userId;

  public String getInstrumentId() {
    return instrumentId;
  }

  public void setInstrumentId(String instrumentId) {
    this.instrumentId = instrumentId;
  }

  public String getIfsc() {
    return ifsc;
  }

  public void setIfsc(String ifsc) {
    this.ifsc = ifsc;
  }

  public String getCvv() {
    return cvv;
  }

  public void setCvv(String cvv) {
    this.cvv = cvv;
  }

  public String getBankAccountNumber() {
    return bankAccountNumber;
  }

  public void setBankAccountNumber(String bankAccountNumber) {
    this.bankAccountNumber = bankAccountNumber;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public InstrumentType getInstrumentType() {
    return instrumentType;
  }

  public void setInstrumentType(InstrumentType instrumentType) {
    this.instrumentType = instrumentType;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
