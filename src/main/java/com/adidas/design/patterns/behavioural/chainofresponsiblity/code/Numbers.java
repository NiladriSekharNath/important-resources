package com.adidas.design.patterns.behavioural.chainofresponsiblity.code;

public class Numbers {
    private int number1;

    private int number2;

    private String calculationWanted;

    public Numbers(int number1, int number2, String calculationWanted){
        this.number1 = number1;
        this.number2 = number2;
        this.calculationWanted = calculationWanted;

    }

    public int getNumber1(){ return this.number1; }

    public int getNumber2(){ return this.number2; }

    public String getCalculationWanted () { return this.calculationWanted; }

}
