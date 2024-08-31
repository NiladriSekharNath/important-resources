package com.adidas.design.patterns.behavioural.chainofresponsiblity.code;

public interface Chain {

    public void setNextChain(Chain nextChain);

    public void calculate(Numbers request);
}
