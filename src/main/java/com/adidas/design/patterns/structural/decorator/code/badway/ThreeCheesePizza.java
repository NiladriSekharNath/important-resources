package com.adidas.design.patterns.structural.decorator.code.badway;

/**
 * If we go down this road we would have to create new subclasses such as this one
 * for every new type of Pizza that we create
 *
 * Now suppose one usecase comes where one of the topping's price increases so for all of the implementations we would have to
 * make changes to adjust for the one change in Price
 */
public class ThreeCheesePizza extends Pizza {
    @Override
    public void setDescription(String newDescription) {

    }

    @Override
    public String getDescription() {
        return "Mozzarella, Fontina, Parmesan, Cheese Pizza";
    }

    @Override
    public double getCost() {
        return 10.00;
    }
}
