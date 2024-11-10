package com.adidas.solidprincipals.interfacesegmentation.betterway.implementation2;

import lombok.extern.slf4j.Slf4j;

/**
 * this implementation is like the abstract factory design pattern
 */
@Slf4j
public class HayatRestaurant extends Restaurant{

  private TrainingSchoolFactory trainingSchoolFactory;
  public HayatRestaurant(TrainingSchoolFactory trainingSchoolFactory){
    this.trainingSchoolFactory = trainingSchoolFactory;
  }
  @Override
  public void hireEmployees() {

    log.info("Hiring employees");

    super.chef = trainingSchoolFactory.chefEmployee();
    super.waiter = trainingSchoolFactory.waiterEmployee();
  }
}
