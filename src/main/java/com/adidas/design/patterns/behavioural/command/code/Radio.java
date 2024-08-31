package com.adidas.design.patterns.behavioural.command.code;

import lombok.extern.slf4j.Slf4j;

/**
 * Just like Television and Radio if we need to implement other electronice devices like this
 * we would do someting like implementing the ElectroniceDevice and implement the features methods like as shown
 *
 */
@Slf4j
public class Radio implements ElectronicDevice{

    private int volume = 0;
    @Override
    public void on() {
        log.info("Radio is On");
    }

    @Override
    public void off() {
        log.info("Radio is Off");
    }

    @Override
    public void volumeUp() {
        volume++;
        log.info("Radio volume is at : {}", volume);
    }

    @Override
    public void volumeDown() {
        volume++;
        log.info("Radio volume is at : {}", volume);
    }
}
