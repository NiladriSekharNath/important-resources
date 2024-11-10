package com.adidas.design.patterns.behavioural.chainofresponsiblity.code;

public class TestChainCalculation {

    public static void main(String args[] ){
        Chain chainCalc1 = new AddNumbers();
        Chain chainCalc2 = new SubtractNumbers();
        Chain chainCalc3 = new MultiplyNumbers();
        Chain chainCalc4 = new DivideNumbers();

        chainCalc1.setNextChain(chainCalc2);
        chainCalc2.setNextChain(chainCalc3);
        chainCalc3.setNextChain(chainCalc4);

        Numbers request1 = new Numbers(4, 2, "multiply");
        Numbers request2 = new Numbers(8, 2, "multiply");

        chainCalc1.calculate(request1);


    }
}
