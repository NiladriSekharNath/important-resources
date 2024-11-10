package com.adidas.lld.carrentalsystem;

import java.util.List;

public abstract class VehicleInventoryManager {

  List<Vehicle> vehicles ;

  public VehicleInventoryManager(List<Vehicle> vehicles){
    this.vehicles = vehicles;
  }

  /**
   * crud operations to be added here
   */

  public List<Vehicle> getVehicles(){
    return this.vehicles;
  }
}
