package com.adidas.design.patterns.creational.builder.code.withinheritance.withgenerics;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestGenericBuilder {

  public static void main(String args[]) {

    ElectricCar electricCar = ElectricCar.builder().batteryType("Lithium-Ion").color("red").fuelType("electric").build();

    log.info("Electric Car : {}", electricCar);
  }
}
