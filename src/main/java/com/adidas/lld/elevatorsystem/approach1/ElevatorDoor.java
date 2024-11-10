package com.adidas.lld.elevatorsystem.approach1;

public class ElevatorDoor {
  int floor;
  Direction direction;

  public void setDisplay(int floor, Direction direction) {
    this.floor = floor;
    this.direction = direction;
  }

  public void showDisplay(){
    System.out.println(floor);
    System.out.println(direction);
  }

}
