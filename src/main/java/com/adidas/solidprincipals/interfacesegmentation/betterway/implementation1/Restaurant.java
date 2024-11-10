package com.adidas.solidprincipals.interfacesegmentation.betterway.implementation1;

import com.adidas.solidprincipals.interfacesegmentation.betterway.implementation2.WaiterEmployee;

public class Restaurant {
  private WaiterEmployee waiter;
  private ChefEmployee chef;
  public Restaurant(WaiterEmployee waiter, ChefEmployee chef){
    this.waiter = waiter;
    this.chef = chef;
  }
}
