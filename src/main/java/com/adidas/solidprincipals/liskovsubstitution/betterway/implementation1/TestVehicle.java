package com.adidas.solidprincipals.liskovsubstitution.betterway.implementation1;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TestVehicle {
  public static void main(String args[]) {
    List<Vehicle> vehicles = new ArrayList<>();
    vehicles.add(new Car());
    vehicles.add(new Motorcycle());
    vehicles.add(new Bicycle());
    /**
     * This here when we have added here causes problems as Bicycle class has no Engine so when we
     * call the hasEngine() . toString() this would cause null pointer problems
     * vehicles.add(new Bicycle());
     *
     * This violates the Liskov Substitution principle which is
     * subtypes should be substitutable by Parent class
     *
     * which is what we are saying is that if we have the Vehicle class that can do the following
     * we should be having the all the Vehicle subclasses be doing the same thing without causing problems
     * but here is the problem as Bicycle is subtype of the vehicle class it will cause problems here
     *
     * This one we have corrected which does not violate the Liskov substitution principle
     *
     * also one best way to check liskov substitution invalidity is by checking
     * if the number of methods in the parent class and the number of methods in child class is greater or equal to
     *
     * like if we have one method 2 methods in parent class and we need to have at least the similar 2 methods implementation in child class
     */
    for (Vehicle vehicle : vehicles) {
      log.info(vehicle.getNumberOfWheels().toString());
    }


  }
}
