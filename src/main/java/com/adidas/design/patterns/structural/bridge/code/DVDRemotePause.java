package com.adidas.design.patterns.structural.bridge.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DVDRemotePause extends RemoteButton {

    public DVDRemotePause(EntertainmentDevice theDevice) {
        super(theDevice);
    }

    @Override
    public void buttonNinePressed() {
        log.info("DVD paused ");
    }
}
