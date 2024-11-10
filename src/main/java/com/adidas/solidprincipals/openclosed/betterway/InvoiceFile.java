package com.adidas.solidprincipals.openclosed.betterway;

import com.adidas.solidprincipals.solid.betterway.Invoice;

public class InvoiceFile implements InvoiceSaver{

  private Invoice invoice;

  public InvoiceFile(Invoice invoice){
    this.invoice = invoice;
  }
  @Override
  public void save() {
    /***
     * save invoice to file implementation
     */
  }
}
