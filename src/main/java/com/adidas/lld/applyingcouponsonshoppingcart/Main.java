package com.adidas.lld.applyingcouponsonshoppingcart;

public class Main {
  public static void main(String[] args){
    Product item1 = new Fan("FAN", 1000, ProductType.ELECTRONIC_GOODS);
    Product item2 = new Sofa("SOFA", 2000, ProductType.FURNITURE_GOODS);

    ShoppingCart cart = new ShoppingCart();

    cart.addToCart(item1);
    cart.addToCart(item2);

    System.out.println("Total price after discount : " + cart.getTotalPrice());
  }
}
