package com.adidas.solidprincipals.openclosed.betterway;

import com.adidas.solidprincipals.solid.betterway.Invoice;

public class InvoiceDao implements InvoiceSaver{

  private Invoice invoice ;

  public InvoiceDao(Invoice invoice){
    this.invoice = invoice;
  }
  @Override
  public void save() {

    /**
     * saving invoice to DB
     */
  }
}
