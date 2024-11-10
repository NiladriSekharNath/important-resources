package com.adidas.lld.carrentalsystem;

public class Car extends Vehicle{
  public Car(int dailyRentalCost, int hourlyRentalCost) {
    super(new CarType(), dailyRentalCost, hourlyRentalCost);
  }
}
