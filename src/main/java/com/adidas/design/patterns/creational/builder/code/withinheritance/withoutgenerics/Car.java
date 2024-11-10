package com.adidas.design.patterns.creational.builder.code.withinheritance.withoutgenerics;

public class Car extends Vehicle {
  private String make;
  private String model;

  protected Car(CarBuilder carBuilder){
    super(carBuilder);
    this.make = carBuilder.make;
    this.model = carBuilder.model;
  }

  public static CarBuilder builder(){
    return new CarBuilder();
  }
  public static class CarBuilder extends VehicleBuilder {
    protected String make ;
    protected String model;

    @Override
    public CarBuilder fuelType(String fuelType) {
      this.fuelType = fuelType;
      return this;

    }

    @Override
    public CarBuilder colour(String colour) {
      this.colour = colour;
      return this;
    }


    public CarBuilder make(String make) {
      this.make = make;
      return this;
    }

    public CarBuilder model(String model) {
      this.model = model;
      return this;
    }

    public Car build() {
      return new Car(this);
    }
  }
}
