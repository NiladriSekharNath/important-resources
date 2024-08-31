package com.adidas.design.patterns.structural.bridge.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TVRemoveMute extends RemoteButton {
    public TVRemoveMute(EntertainmentDevice theDevice) {
        super(theDevice);
    }

    @Override
    public void buttonNinePressed() {
        log.info("TV was muted ");
    }
}
