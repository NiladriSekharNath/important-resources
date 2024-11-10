package com.adidas.lld.paymentgateway.code.instrument;

public class CardInstrument extends Instrument {
  private String cardNumber;
  private String cvvNumber;

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getCvvNumber() {
    return cvvNumber;
  }

  public void setCvvNumber(String cvvNumber) {
    this.cvvNumber = cvvNumber;
  }
}
