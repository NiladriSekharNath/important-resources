package com.adidas.design.patterns.creational.builder.code.withinheritance.withgenerics;

import lombok.ToString;

@ToString(callSuper = true)
public class ElectricCar extends Car {
  private String batteryType;

  public String getBatteryType() {
    return batteryType;
  }

  protected ElectricCar(Builder builder) {
    super(builder);
    this.batteryType = builder.batteryType;
  }

  public static Builder<?> builder(){
    return new Builder();
  }

  public static class Builder<T extends Builder<T>> extends Car.Builder<T> {
    protected String batteryType;

    public T batteryType(String batteryType) {
      this.batteryType = batteryType;
      return self();
    }

    @Override
    public ElectricCar build() {
      return new ElectricCar(this);
    }
  }
}
