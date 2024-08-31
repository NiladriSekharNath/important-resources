package com.adidas.design.patterns.structural.bridge.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DVDDevice extends EntertainmentDevice {
    public DVDDevice(int deviceState, int maxSetting){
        super.deviceState = deviceState;
        super.maxSetting = maxSetting;
    }
    @Override
    public void buttonFivePressed() {
        log.info("DVD skips to previous chapter, current Channel: {}", deviceState);

        deviceState--;
    }

    @Override
    public void buttonSixPressed() {
        log.info("DVD skips to next chapter, Current channel: {}", deviceState);
        deviceState++ ;
    }
}
