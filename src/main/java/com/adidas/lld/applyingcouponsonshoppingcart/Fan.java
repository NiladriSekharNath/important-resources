package com.adidas.lld.applyingcouponsonshoppingcart;

public class Fan extends Product{
  Fan(String name, double price, ProductType type) {
    super(name, price, type);
  }

  @Override
  public double getPrice() {
    return 0;
  }
}
