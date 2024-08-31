package com.adidas.design.patterns.behavioural.mediator.code;

import lombok.extern.slf4j.Slf4j;

/**
 * like a name of a person here and not the company
 */
@Slf4j
public class GoldmanSachs extends Colleague {
    public GoldmanSachs(Mediator mediator) {
        super(mediator);
        log.info("Gorman Slacks signed up for the exchange ");
    }
}
