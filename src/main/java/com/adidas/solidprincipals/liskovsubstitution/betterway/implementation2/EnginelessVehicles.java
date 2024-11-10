package com.adidas.solidprincipals.liskovsubstitution.betterway.implementation2;

public abstract class EnginelessVehicles extends Vehicle{
  private TyreFactory tyreFactory;

  public EnginelessVehicles(TyreFactory tyreFactory){
    this.tyreFactory = tyreFactory;
  }
  @Override
  public void setTyres() {
    super.tyres = tyreFactory.makeTyres();
  }
}
