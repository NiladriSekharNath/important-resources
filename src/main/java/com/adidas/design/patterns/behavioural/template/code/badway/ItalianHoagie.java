package com.adidas.design.patterns.behavioural.template.code.badway;

import lombok.extern.slf4j.Slf4j;

/**
 * this is the bad way to make this class we will show the better way
 *
 * P.S. -> Hoagie means a sandwich
 */
@Slf4j
public class ItalianHoagie {

    /**
     * now the problem with implementing these methods in these particular way is
     * the main problem let's say we have a different type of sandwich which does not
     * use condiments or let's say vegetables we would have to rewrite few of the methods
     * which is bad
     */
    public void makeSandwich() {
       /*
        cutBun();
        addMeat();
        addCheese();
        addVegetables();
        addCondiments();
        wrapTheHoagie();*/
    }

    private void cutBun() {
        log.info("The Hoagie is cut");
    }
}
