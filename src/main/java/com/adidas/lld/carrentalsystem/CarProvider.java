package com.adidas.lld.carrentalsystem;

import java.util.ArrayList;
import java.util.List;

public class CarProvider implements VehiclesProvider{
  @Override
  public List<Vehicle> getVehicles() {
    return new ArrayList<>();
  }
}
