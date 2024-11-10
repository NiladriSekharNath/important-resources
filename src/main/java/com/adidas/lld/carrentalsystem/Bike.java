package com.adidas.lld.carrentalsystem;

public class Bike extends Vehicle{
  public Bike(int dailyRentalCost, int hourlyRentalCost) {
    super(new BikeType(), dailyRentalCost, hourlyRentalCost);
  }
}
