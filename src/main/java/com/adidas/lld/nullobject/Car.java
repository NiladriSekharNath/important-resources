package com.adidas.lld.nullobject;

public class Car implements Vehicle{
  @Override
  public int engineCapacity() {
    return 2200;
  }

  @Override
  public int numberOfWheels() {
    return 4;
  }
}
