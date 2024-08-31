package com.adidas.design.patterns.behavioural.command.code;

/**
 * Electronice Device interfaces has all these methods which all our receivers
 * like TV Radio has receivers
 */
public interface ElectronicDevice {

    public void on();
    public void off();
    public void volumeUp();
    public void volumeDown();

}
