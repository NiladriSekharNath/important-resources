package com.adidas.lld.applyingcouponsonshoppingcart;

public class Sofa extends Product{

  Sofa(String name, double price, ProductType type) {
    super(name, price, type);
  }

  @Override
  public double getPrice() {
    return 0;
  }
}
