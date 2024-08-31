package com.adidas.design.patterns.creational.abstractfactory.code;

// Any part that implements the interface ESEngine
// can replace that part in any ship

public interface ESEngine {

    // User is forced to implement this method
    // It outputs the engineDetails as implemented

    public String getEngineDetails();
}
