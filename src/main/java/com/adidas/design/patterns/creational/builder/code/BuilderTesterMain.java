package com.adidas.design.patterns.creational.builder.code;

import com.adidas.design.patterns.creational.builder.code.withinheritance.withoutgenerics.Car;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class BuilderTesterMain {
    private static Random random = new Random();

    public static void main(String args[]) {

        House newHouse1 = House.builder()
            .houseType("Prime Sea View Duplex")
            .rooms(2)
            .windows(1)
            .swimmingPool(true)
            .optionals("2 car garage")
            .build();

        log.info("Our new House created: {}", newHouse1);


        /**
         * other way of making the builder class is via the director class there are not many use cases found for this
         *
         */

        Meal meal = new MealDirector(new NonVegMealBuilder()).prepareMeal();

        log.info("New non Veg Meal : {}", meal);

        /**
         *  okay this is the main problem here when we try to
         *  chain the methods of VehicleBuilder with CarBuilder this would not work with the
         *  setup because in VehicleBuilder we are returning the VehicleBuilder object and using
         *
         *  Car car = Car.builder().make("Ford").colour("color").fuelType("petrol").build();
         *
         *  the methods particular to the, fuelType("petrol") would return the VehicleBuilder object which is again not compatible
         *
         *  with CarBuilder build() method
         *
         *  so solution is we would have to override the methods of VehicleBuilder in the CarBuilder object
         *
         *
         * after overriding the existing solution is
       */

        Car car = Car.builder()
            .fuelType("Petrol")
            .colour("red")
            .make("BMW")
            .model("M5 CS")
            .build();

        /**
         * Now similarly we would have to do the same thing if we have an Electric Car that extends the Car class
         * 
         */
    }

}
