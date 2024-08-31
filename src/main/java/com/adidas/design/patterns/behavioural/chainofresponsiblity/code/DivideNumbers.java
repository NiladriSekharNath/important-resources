package com.adidas.design.patterns.behavioural.chainofresponsiblity.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DivideNumbers implements Chain {
    private Chain nextInChain;
    @Override
    public void setNextChain(Chain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void calculate(Numbers request) {
        if(request.getCalculationWanted() == "divide"){
            log.info("{} / {} = {}", request.getNumber1(), request.getNumber2(), (request.getNumber1() / request.getNumber2()));
        }
        else {
            log.info("Only works for add, subtract, multiply, divide");
        }
    }
}
