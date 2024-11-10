package com.adidas.lld.ordermanagementsystem;

public class CardPaymentMode implements PaymentMode{
  @Override
  public boolean makePayment() {
    return true;
  }
}
