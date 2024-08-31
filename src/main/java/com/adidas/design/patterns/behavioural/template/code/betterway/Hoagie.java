package com.adidas.design.patterns.behavioural.template.code.betterway;

import lombok.extern.slf4j.Slf4j;

/**
 * Also we could see this implementation inside the refactoring.guru template method in Java
 */
@Slf4j
public abstract class Hoagie {
    /**
     * making this method final because we are not giving option to change the
     * process of making the sandwich
     *
     * this is called the template method where in we define the steps that need to be followed all times
     */
    final void makeSandwich() {
        cutBun();
        /**
         * these hooks used here
         */
        if (customerWantsMeat()) {
            addMeat();
        }
        if (customerWantsCheese()) {
            addCheese();
        }
        if (customerWantsVegetables()) {
            addVegetables();
        }
        if (customerWantsCondiments()) {
            addCondiments();
        }

        wrapTheHoagie();

    }

    public void cutBun() {
        log.info("The Hoagie is cut ");
    }

    abstract void addMeat();

    abstract void addCheese();

    abstract void addVegetables();

    abstract void addCondiments();

    /**
     * can be overriden by the base classes extending this class
     * this is also call a hook, where using the hook we would perform the next set of actions
     */
    boolean customerWantsMeat() {
        return true;
    }

    boolean customerWantsCheese() {
        return true;
    }

    boolean customerWantsVegetables() {
        return true;
    }

    boolean customerWantsCondiments() {
        return true;
    }

    /**
     * these methods are called definites, which means that they are always present
     */
    public void wrapTheHoagie() {
        log.info("Wrap the Hoagie");
    }
}
