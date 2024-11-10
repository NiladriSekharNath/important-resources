package com.adidas.solidprincipals.solid.betterway;

/**
 * Solid pricipal:
 *
 * S -> Single Responsibility Principal : A class should have only one reason to change
 *
 * so what we need to understand that a class should have only one reason to change it's
 * behaviour, by that if we are changing the calculateTotal() function with a functionality
 * like introducing the tax, calculating the discount and what not then the function
 * calculateTotal can change
 *
 * Other functions let's say the other function, printing of the Invoice() if the logic changes then as it is part of the same class
 * it should not or in other words we should not keep this function here as it might change apart from the calculateTotal() function which could also
 * change
 *
 * same goes for the saveToDB() function if we change something like introducing functionality to change/add new items to save to DB or introduce
 * new feature to save to file or something then this function should also not be here
 *
 * which is not done here as shown
 *
 * also since it has only one functionality here we could simply call this InvoiceCalculator or something
 *
 * Advantages of this approach :
 *  - Avoid Duplicate code
 *  - Easy to maintain
 *  - Easy to understand
 *  - Flexible Software
 *  - Reduce Complexity 
 *
 */
public class Invoice {

  /**
   * Here we say that this invoice class has a relationship
   */
  private Marker marker;
  private int quantity;

  public Invoice(Marker marker, int quantity){
    this.marker = marker;
    this.quantity = quantity;

  }


  /**
   *
   * @return the total price of the marker with the number of quantities
   *
   *
   *
   */
  public int calculateTotalPrice(){
    int price = ((this.marker.getPrice()) * this.quantity);
    return price;
  }
}

