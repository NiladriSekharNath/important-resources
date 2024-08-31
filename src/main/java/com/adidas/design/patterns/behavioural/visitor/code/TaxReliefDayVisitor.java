package com.adidas.design.patterns.behavioural.visitor.code;

import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;

/**
 * let's say govt asks us to create a day where the Tax is less for certain items
 *
 * so we create a different visitor here and change the values of the task for the products on different days
 */
@Slf4j
public class TaxReliefDayVisitor implements Visitor {

    DecimalFormat df = new DecimalFormat("#.##");

    public TaxReliefDayVisitor() {

    }

    @Override
    public double visit(Liquor liquorItem) {
        log.info("Liquor item: Price with Tax ");
        return Double.parseDouble(df.format((liquorItem.getPrice() * .10) + liquorItem.getPrice()));
    }

    @Override
    public double visit(Tobacco tobaccoItem) {
        log.info("Liquor item: Price with Tax ");
        return Double.parseDouble(df.format((tobaccoItem.getPrice() * .16) + tobaccoItem.getPrice()));

    }

    @Override
    public double visit(Necessity necessityItem) {
        log.info("Necessity item: Price with Tax ");
        return Double.parseDouble(df.format((necessityItem.getPrice() * 0) + necessityItem.getPrice()));
    }
}
