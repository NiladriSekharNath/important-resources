package com.adidas.design.patterns.behavioural.visitor.code;

public interface Visitor {

    public double visit(Liquor liquorItem);

    public double visit(Tobacco tobaccoItem);

    public double visit(Necessity necessityItem);
}
