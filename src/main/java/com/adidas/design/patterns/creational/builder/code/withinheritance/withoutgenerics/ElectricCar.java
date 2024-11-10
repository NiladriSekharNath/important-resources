package com.adidas.design.patterns.creational.builder.code.withinheritance.withoutgenerics;

public class ElectricCar extends Car{
  private String batteryType;

  public String getBatteryType(){
    return this.batteryType;
  }

  public void setBatteryType(String batteryType){
    this.batteryType = batteryType;
  }

  protected ElectricCar(ElectricCarBuilder electricCarBuilder){
    super(electricCarBuilder);
    this.batteryType = electricCarBuilder.batteryType;

  }

  public static ElectricCarBuilder builder(){
    return new ElectricCarBuilder();
  }

  public static class ElectricCarBuilder extends CarBuilder{

    /**
     * here we would have to override all these methods for method chaining in the
     * again from the VehicleBuilder class and CarBuilderClass
     */
    private String batteryType;

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

    @Override
    public CarBuilder make(String make) {
      this.make = make;
      return this;
    }

    @Override
    public CarBuilder model(String model) {
      this.model = model;
      return this;
    }


    public ElectricCarBuilder batteryType(String batteryType){
      this.batteryType = batteryType;
      return this;
    }

    public ElectricCar build(){
      return new ElectricCar(this);
    }
  }
}
