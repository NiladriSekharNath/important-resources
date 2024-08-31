package com.adidas.design.patterns.structural.bridge.code;

import lombok.extern.slf4j.Slf4j;

/**
 * this is called as the implementer and the class inheriting from this is called the
 * Concrete Implementer
 */
@Slf4j
public abstract class EntertainmentDevice {
    /**
     * the current state of the device for a TV it would be the current channel or for a
     * DVD player current channel, could be anything that is in a particular order
     */
    protected int deviceState;

    /**
     * highest number of channels or for a DVD player the highest number of channel in the DVD player
     */
    protected int maxSetting;
    protected int volumeLevel = 0;

    public abstract void buttonFivePressed();

    public abstract void buttonSixPressed();

    public void deviceFeedback() {
        if (deviceState > maxSetting || deviceState < 0) {
            deviceState = 0;
            log.info("On Current : {}", deviceState);
        }
    }

    public void buttonSevenPressed() {
        volumeLevel++;
        log.info("Volume currently at : {} ", volumeLevel);
    }

    public void buttonEightPressed() {
        volumeLevel--;
        log.info("Volume currently at : {} ", volumeLevel);
    }
}
