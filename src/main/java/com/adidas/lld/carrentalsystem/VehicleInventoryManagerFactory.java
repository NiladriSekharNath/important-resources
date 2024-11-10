package com.adidas.lld.carrentalsystem;

public class VehicleInventoryManagerFactory {

  private VehicleInventoryManager vehicleInventoryManager;

  public VehicleInventoryManager getVehicleInventoryManager(String vehicleType){
    VehiclesProvider vehiclesProvider ;
    if("bike".equalsIgnoreCase(vehicleType)){
      vehiclesProvider = new BikesProvider();
      vehicleInventoryManager = new BikeInventoryManager(vehiclesProvider.getVehicles());
    }
    else if("car".equalsIgnoreCase(vehicleType)){
      vehiclesProvider = new CarProvider();
      vehicleInventoryManager = new CarInventoryManager(vehiclesProvider.getVehicles());
    }
    return vehicleInventoryManager;
  }
}
