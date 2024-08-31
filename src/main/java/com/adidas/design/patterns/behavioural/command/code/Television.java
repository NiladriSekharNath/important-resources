package com.adidas.design.patterns.behavioural.command.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Television implements ElectronicDevice {

    private int volume = 0;

    @Override
    public void on() {
        log.info("TV is On");
    }

    @Override
    public void off() {
        log.info("TV is Off");
    }

    @Override
    public void volumeUp() {
        volume++;
        log.info("TV volume is at : {}", volume);
    }

    @Override
    public void volumeDown() {
        volume--;
        log.info("TV volume is at : {} ", volume);
    }
}
