package com.adidas.design.patterns.creational.abstractfactory.code;

// Here we define a basic component of a space ship
// Any part that implements the interface ESEngine
// can replace that part in any ship

public class ESUFOEngine implements ESEngine{

    // EnemyShip contains a reference to the object
    // ESWeapon. It is stored in the field weapon

    // The Strategy design pattern is being used here

    // When the field that is of type ESUFOGun is printed
    // the following implementation is shown

    @Override
    public String getEngineDetails() {
        return "ESUFOEngine has movement speed 500 mph";
    }
}
