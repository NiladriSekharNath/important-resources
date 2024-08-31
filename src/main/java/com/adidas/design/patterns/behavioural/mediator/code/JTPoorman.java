package com.adidas.design.patterns.behavioural.mediator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JTPoorman extends Colleague{
    public JTPoorman(Mediator mediator) {
        super(mediator);

        log.info("JTPoorman signed up for the exchange");
    }
}
