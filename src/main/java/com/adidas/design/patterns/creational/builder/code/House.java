package com.adidas.design.patterns.creational.builder.code;

import lombok.Builder;
import lombok.ToString;

/**
 * Standard way of creating a builder pattern
 * in Java
 */
@ToString
public class House {
    private String houseType;
    private int rooms;
    private int windows;
    private int bathrooms;
    private boolean swimmingPool;
    private String optionals;

    public String getHouseType(){
        return this.houseType;
    }
    public int getRooms() {
        return this.rooms;
    }

    public int getWindows() {
        return this.windows;
    }

    public int getBathrooms() {
        return this.bathrooms;
    }

    public boolean isSwimmingPool(){
        return this.swimmingPool;
    }

    public String getOptionals(){
        return this.optionals;
    }

    private House(HouseBuilder houseBuilder){
        /**
         * we can add the validations here to throw problems
         */
        this.houseType = houseBuilder.houseType;
        this.rooms = houseBuilder.rooms;
        this.windows = houseBuilder.windows;
        this.bathrooms = houseBuilder.bathrooms;
        this.swimmingPool = houseBuilder.swimmingPool;
        this.optionals = houseBuilder.optionals;
    }

    public static HouseBuilder builder(){
        return new HouseBuilder();
    }

    public static class HouseBuilder{

        private String houseType;
        private int rooms;
        private int windows;
        private int bathrooms;
        private boolean swimmingPool;
        private String optionals;

        public HouseBuilder houseType(String houseType){
            this.houseType = houseType ;
            return this;
        }

        /**
         * normally we use setter methods with void return type but in the case of builder pattern when we need to return
         * the next builder we would have to do the following
         *
         *
         *
         *

        public void setRooms(int rooms){
            this.rooms = rooms;
        }

         and we are doing this way instead of "setRooms(int rooms)" "just room(int rooms)" we will show because

         House newHouse1 = House.builder()
         .rooms(2)
         .windows(1)
         .swimmingPool(true)
         .optionals("2 car garage")
         .build();

         enable us to write like above instead of

         House newHouse1 = House.builder()
         .setRooms(2)
         .setWindows(1)
         .setSwimmingPool(true)
         .setOptionals("2 car garage")
         .build();

        */
        public HouseBuilder rooms(int rooms){
            this.rooms = rooms;
            return this;
        }

        public HouseBuilder windows(int windows){
            this.windows = windows;
            return this;
        }

        public HouseBuilder bathrooms(int bathrooms){
            this.bathrooms = bathrooms;
            return this;
        }

        public HouseBuilder swimmingPool(boolean swimmingPool){
            this.swimmingPool = swimmingPool;
            return this;
        }

        public HouseBuilder optionals(String optionals){
            this.optionals = optionals;
            return this;
        }

        public House build(){
            return new House(this);
        }
    }
}
