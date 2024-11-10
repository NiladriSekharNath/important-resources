package com.adidas.solidprincipals.interfacesegmentation.badway;

/**
 * this is the bad way to implement a functionality here
 *
 * as according to the Interface Segmentation principal we should not be such
 *
 * that client should not implement unnecessary functions that they do not need
 * we should break this to appropriate smaller interfaces
 */
public class Waiter implements RestaurantEmployee {
  @Override
  public void takeOrders() {

  }

  @Override
  public void serveCustomers() {

  }

  @Override
  public void prepareMenu() {

  }

  @Override
  public void cookFood() {

  }
}
