package com.adidas.design.patterns.behavioural.visitor.code;

import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;

@Slf4j
public class TaxVisitor implements Visitor {

    DecimalFormat df = new DecimalFormat("#.##");

    public TaxVisitor(){

    }
    @Override
    public double visit(Liquor liquorItem) {
        log.info("Liquor item: Price with Tax ");
        return Double.parseDouble(df.format((liquorItem.getPrice() * .18) + liquorItem.getPrice()));
    }

    @Override
    public double visit(Tobacco tobaccoItem) {
        log.info("Liquor item: Price with Tax ");
        return Double.parseDouble(df.format((tobaccoItem.getPrice() * .32) + tobaccoItem.getPrice()));

    }

    @Override
    public double visit(Necessity necessityItem) {
        log.info("Necessity item: Price with Tax ");
        return Double.parseDouble(df.format((necessityItem.getPrice() * 0) + necessityItem.getPrice()));
    }
}
