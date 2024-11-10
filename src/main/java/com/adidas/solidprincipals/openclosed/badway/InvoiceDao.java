package com.adidas.solidprincipals.openclosed.badway;

import com.adidas.solidprincipals.solid.betterway.Invoice;

/**
 *
 * Bad way to introduce new method "saveToFile()" here which will probably cause
 * things to break in already tested code and it is not right, we should not try to modify already tested code
 *
 * This is the Open/Closed Principle which is open for extension and closed for modification
 */
public class InvoiceDao {

  private Invoice invoice;

  public void saveToDB(){
    // save Invoice to DB
  }

  /***
   *
   * bad way here to introduce this new method here
   */
  public void saveToFile(){
    //
  }

}
