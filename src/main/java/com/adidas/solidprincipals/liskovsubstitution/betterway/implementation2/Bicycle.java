package com.adidas.solidprincipals.liskovsubstitution.betterway.implementation2;

public abstract class Bicycle extends EnginelessVehicles {

  public Bicycle(TyreFactory tyreFactory) {
    super(tyreFactory);
  }
}
