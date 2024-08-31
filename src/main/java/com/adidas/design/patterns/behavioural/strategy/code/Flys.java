package com.adidas.design.patterns.behavioural.strategy.code;

/**
 * This is the Strategy Pattern that we are using here extracting the idea
 * like for a payment gateway for types of payments, for an Order, like
 * Pay via CreditCard, Pay Via PayPal, Pay Via UPI, etc
 * we are taking the common strategy out that is payment and adding it to the order
 * or extracting the behaviour types (types of payments) or for moving to a
 * particular destination by types (car, bicycle, walking) or in this simple case
 * animal can fly or not (Strategy for flying etc)
 *
 * The help with this is suppose for again
 * taking the payment example we have let's say for an app
 * in the starting phase we have support for CreditCard and PayPal later
 * we use GooglePay after few months so the next programmer can simply
 * extend the PaymentGateway Interface (or let's say this Flys interface)
 * to add more support, so this way the main class
 * like the order class or the Animal class gets affected
 *
 */
public interface Flys {
    String fly();
}
