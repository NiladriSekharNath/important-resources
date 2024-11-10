package com.adidas.solidprincipals.liskovsubstitution.betterway.implementation2;

public abstract class Car extends EngineVehiclesAssembly{
  public Car(TyreFactory tyreFactory, EngineFactory engineFactory) {
    super(tyreFactory, engineFactory);
  }
}
