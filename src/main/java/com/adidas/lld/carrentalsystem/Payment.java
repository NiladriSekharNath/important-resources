package com.adidas.lld.carrentalsystem;

import java.util.Date;

public class Payment {
  int paymentId;
  Date paymentDate;
  boolean isRefundable;
  PaymentMethod paymentMethod;

  Bill bill;

  public Payment(PaymentMethod paymentMethod, Bill bill){
    this.paymentMethod = paymentMethod;
    this.bill = bill;
  }

  public boolean isBillPaid(){
    return true;
  }
}
