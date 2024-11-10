package com.adidas.solidprincipals.dependencyinversion.betterway;

public class Macbook {

  public Keyboard keyboard;

  public Mouse mouse;

  public Macbook(Keyboard keyboard, Mouse mouse){
    this.keyboard = keyboard;
    this.mouse = mouse;
  }
}
