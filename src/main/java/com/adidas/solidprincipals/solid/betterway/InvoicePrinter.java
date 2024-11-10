package com.adidas.solidprincipals.solid.betterway;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvoicePrinter {

  private Invoice invoice;

  public InvoicePrinter(Invoice invoice){
    this.invoice = invoice;
  }

  public void printPrice(){
    log.info("Price of the article : {}", this.invoice.calculateTotalPrice());
  }
}
