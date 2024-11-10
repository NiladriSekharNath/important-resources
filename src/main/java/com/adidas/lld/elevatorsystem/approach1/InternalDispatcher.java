package com.adidas.lld.elevatorsystem.approach1;

import java.util.List;

public class InternalDispatcher {
  List<ElevatorController> elevatorControllerList;

  public InternalDispatcher() {
    this.elevatorControllerList = ElevatorCreator.getElevatorControllerList();
  }

  public void submitInternalRequest(int floor, ElevatorCar elevatorCar){

  }
}
