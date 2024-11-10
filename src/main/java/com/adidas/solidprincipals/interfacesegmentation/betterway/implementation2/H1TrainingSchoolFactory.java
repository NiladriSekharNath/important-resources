package com.adidas.solidprincipals.interfacesegmentation.betterway.implementation2;

public class H1TrainingSchoolFactory implements TrainingSchoolFactory {
  @Override
  public ChefEmployee chefEmployee() {
    return new ChefFromH1TrainingSchool();
  }

  @Override
  public WaiterEmployee waiterEmployee() {
    return new WaiterFromH1TrainingSchool();
  }
}
