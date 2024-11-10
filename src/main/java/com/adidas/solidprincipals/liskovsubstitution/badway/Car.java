package com.adidas.solidprincipals.liskovsubstitution.badway;

public class Car extends Vehicle {

  @Override
  public Integer getNumberOfWheels() {
    return 4;
  }
}
