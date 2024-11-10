package com.adidas.design.patterns.creational.builder.code.withinheritance.withoutgenerics;

public class Vehicle {

  private String fuelType;
  private String colour;

  // Standard Getter methods..
  protected Vehicle(VehicleBuilder builder) {
    this.colour = builder.colour;
    this.fuelType = builder.fuelType;
  }

  public static VehicleBuilder builder(){
    return new VehicleBuilder();
  }

  public static class VehicleBuilder {

    protected String fuelType;
    protected String colour;

    public VehicleBuilder fuelType(String fuelType) {
      this.fuelType = fuelType;
      return this;

    }
    public VehicleBuilder colour(String colour) {
      this.colour = colour;
      return this;
    }

    public Vehicle build() {
      return new Vehicle(this);
    }
  }
}