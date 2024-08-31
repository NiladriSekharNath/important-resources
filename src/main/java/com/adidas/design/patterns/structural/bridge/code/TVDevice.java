package com.adidas.design.patterns.structural.bridge.code;

import lombok.extern.slf4j.Slf4j;

/**
 * this class is called the concrete implementer
 */
@Slf4j
public class TVDevice extends EntertainmentDevice{


    public TVDevice(int deviceState, int maxSetting){
        super.deviceState = deviceState;
        super.maxSetting = maxSetting;
    }

    @Override
    public void buttonFivePressed() {
        log.info("Channel Down, current Channel: {}", deviceState);

        deviceState--;
    }

    @Override
    public void buttonSixPressed() {
        log.info("Channel up, Current channel: {}", deviceState);
        deviceState++ ;
    }
}
