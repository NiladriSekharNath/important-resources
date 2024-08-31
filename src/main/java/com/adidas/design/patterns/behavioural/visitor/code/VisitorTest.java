package com.adidas.design.patterns.behavioural.visitor.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VisitorTest {

    public static void main(String args []){

        TaxVisitor taxCalc = new TaxVisitor();
        TaxReliefDayVisitor taxReliefDayVisitor = new TaxReliefDayVisitor();

        Necessity milk = new Necessity(3.46);
        Liquor vodka = new Liquor(11.99);
        Tobacco cigars = new Tobacco(19.99);

        log.info("Normal Tax Day ");

        log.info("Milk price with Tax: {}", milk.accept(taxCalc));
        log.info("Vodka Price with Tax: {}", vodka.accept(taxCalc));
        log.info("Cigar Price with Tax: {}", cigars.accept(taxCalc));

        log.info("Tax Holiday Prices ");

        log.info("Milk price with Tax: {}", milk.accept(taxReliefDayVisitor));
        log.info("Vodka Price with Tax: {}", vodka.accept(taxReliefDayVisitor));
        log.info("Cigar Price with Tax: {}", cigars.accept(taxReliefDayVisitor));
    }
}
