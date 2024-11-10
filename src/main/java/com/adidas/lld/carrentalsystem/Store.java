package com.adidas.lld.carrentalsystem;

import java.util.ArrayList;
import java.util.List;

public class Store {
  int storeId;
  VehicleInventoryManager inventoryManager;
  Location storeLocation ;

  VehicleInventoryManagerFactory vehicleInventoryManagerFactory;
  List<Reservation> reservations;

  public Store(){
    this.vehicleInventoryManagerFactory = new VehicleInventoryManagerFactory();
    this.reservations = new ArrayList<>();
  }

  public List<Vehicle> getVehicles(VehicleType vehicleType){
    this.inventoryManager = this.vehicleInventoryManagerFactory.getVehicleInventoryManager(vehicleType.getVehicleType());
    return this.inventoryManager.getVehicles();
  }

  public Reservation createReservation(Vehicle vehicle, User user, String typeOfReservation){
    Reservation reservation = new Reservation();
    reservation.createReservation(user, vehicle, typeOfReservation);
    return reservation ;
  }

  public boolean completeReservation(int reservationID) {

    //take out the reservation from the list and call complete the reservation method.
    return true;
  }

}
