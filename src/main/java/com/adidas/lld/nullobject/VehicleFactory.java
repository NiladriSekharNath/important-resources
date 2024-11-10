package com.adidas.lld.nullobject;

public class VehicleFactory {
  public static Vehicle getVehicle(String typeOfVehicle){
    if(typeOfVehicle.equalsIgnoreCase("Car")){
      return new Car();

    }
    /***
     * we can return from null which we would have done originally otherwise we should return like this we did here
     *
     */
    return new NullVehicle();

  }
}
