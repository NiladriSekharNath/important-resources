package com.adidas.design.patterns.behavioural.template.code.betterway;

public class SandwichSculptor {

    public static void main(String args[]){
        Hoagie customised12InchHoagie = new ItalianHoagie();
        customised12InchHoagie.makeSandwich();

        Hoagie customisedVeggieSandwich = new VeggieHoagie();
        customisedVeggieSandwich.makeSandwich();
    }
}
