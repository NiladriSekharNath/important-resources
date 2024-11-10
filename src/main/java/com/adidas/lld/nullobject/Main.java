package com.adidas.lld.nullobject;

public class Main {
  public static void main(String args[]){

    Vehicle vehicle1 = VehicleFactory.getVehicle("Bike");

    Vehicle vehicle2 = VehicleFactory.getVehicle("");

    new Main().printVehicleDetails(vehicle2);
  }

  private void printVehicleDetails(Vehicle vehicle) {
    System.out.println(String.format("Vehicle engine capacity: %s", vehicle.engineCapacity()));
    System.out.println(String.format("Vehicle number of Wheels: %s", vehicle.numberOfWheels()));

  }
}
