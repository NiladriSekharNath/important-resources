package com.adidas.solidprincipals.dependencyinversion.betterway;

public class TestMacbookClient {
  public static void main(String args []){
    Macbook macbook = new Macbook(new WiredKeyboard(), new WiredMouse());
  }
}
