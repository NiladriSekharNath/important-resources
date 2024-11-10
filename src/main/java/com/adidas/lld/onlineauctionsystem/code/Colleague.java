package com.adidas.lld.onlineauctionsystem.code;

public interface Colleague {
  void placeBid(int bidAmount);
  void receiveBidNotification(int bidAmount);
  String getName();
}
