package com.adidas.solidprincipals.openclosed.betterway;

/**
 * this way is better implementation as let's say we would have to implement something
 * like a new kind of persistence in the future we would have to simply implement this InvoiceSaver to make this work
 */
public interface InvoiceSaver {

  public void save();
}
