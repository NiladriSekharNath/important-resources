package com.adidas.solidprincipals.dependencyinversion.badway;

/**
 * According to dependency inversion principal
 *
 * class should depend on interfaces rather than on the concrete class
 *
 * Here just to prove the point the WiredKeyboard here is a concrete implementation(not an abstract class or interface)
 * for the keyboard
 */
public class Macbook {

  private WiredKeyboard keyboard;

  private WiredMouse mouse;

  public Macbook(WiredKeyboard keyboard, WiredMouse mouse){
    this.keyboard = keyboard;
    this.mouse = mouse;
  }
}
