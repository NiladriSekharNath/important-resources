package com.adidas.design.patterns.structural.decorator.code.badway;

/**
 * this is the wrong way of doing the decorator let's say for the pIzza example where we are trying to make different pizzas
 * which would have to inherit this Pizza class and override these methods
 *
 * let's say the ThreeCheesePizza extends this Pizza class
 */
public abstract class Pizza {
    public abstract void setDescription(String newDescription);

    public abstract String getDescription();

    public abstract double getCost();

    /**
     *
     * Above problems are still valid which are written in the ThreeCheesePizza.java class but the other problem is
     * if we add hasFontina() method or any other method in the base class we would have to implement this method in
     * all the subclasses and cause problems
     *
     *
     * public abstract boolean hasFontina();
     */
}
