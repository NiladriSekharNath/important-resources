package com.adidas.lld.nullobject;

/**
 * the purpose of using the null object design pattern is that we are able to remove the if checks "if(object!=null)" then it can safely call other methods
 *
 * To achieve this we are doing something like, instead of returning null we are returning the default implementations of these methods
 *
 * hence there is no null check required.
 *
 * we can think like this
 *
 * null object is an object, an object is an instance of a class, a class can have many number of methods, we can simply not create any object (let's say Vehicle vehicle = null, something like this) or we could
 * do like we did here extend the method and place default values
 *
 */
public class NullVehicle implements Vehicle{
  @Override
  public int engineCapacity() {
    return 0;
  }

  @Override
  public int numberOfWheels() {
    return 0;
  }
}
