package com.adidas.solidprincipals.liskovsubstitution.betterway.implementation1;

/**
 * to solve the Liskov substitution problem we would need to solve this way keep the vehicle general(common) information
 * outside like for vehicles like
 */
public class Vehicle {

  public Integer getNumberOfWheels(){
    return 2;
  }
}
