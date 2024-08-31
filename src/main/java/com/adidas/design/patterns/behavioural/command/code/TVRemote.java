package com.adidas.design.patterns.behavioural.command.code;

public class TVRemote {

    /**
     * basically for the TVRemote there is only one kind of receiver The Television
     */
    public static ElectronicDevice getDevice(){
        return new Television();
    }
}
