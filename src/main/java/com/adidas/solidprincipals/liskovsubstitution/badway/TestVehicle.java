package com.adidas.solidprincipals.liskovsubstitution.badway;

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
     * so either we should not make bicycle be a subtype of vehicle or we should fix this code in this place and all other places
     * with some kind of if statement like if(vehicle is instance of Bicycle we should not print )
     */
    for (Vehicle vehicle : vehicles) {
      if (!(vehicle instanceof Bicycle))
        log.info(vehicle.hasEngine().toString());
    }


  }
}
