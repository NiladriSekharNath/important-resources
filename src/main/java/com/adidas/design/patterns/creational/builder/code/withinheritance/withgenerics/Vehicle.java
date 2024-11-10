package com.adidas.design.patterns.creational.builder.code.withinheritance.withgenerics;

import lombok.ToString;

@ToString
public class Vehicle {
  private String color ;
  private String fuelType;

  protected Vehicle(Builder vehicleBuilder){
    this.color = vehicleBuilder.color;
    this.fuelType = vehicleBuilder.fuelType;
  }

  public static class Builder<T extends Builder>{

    private String color ;

    private String fuelType ;

    T self(){
      return (T) this;
    }

    public T color(String color){
      this.color = color;
      return self();
    }

    public T fuelType(String fuelType){
      this.fuelType = fuelType;
      return self();
    }

    public Vehicle build(){
      return new Vehicle(this);
    }
  }
}
