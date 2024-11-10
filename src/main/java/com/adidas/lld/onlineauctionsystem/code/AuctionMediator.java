package com.adidas.lld.onlineauctionsystem.code;

public interface AuctionMediator {
  void addBidder(Colleague bidder);
  void placeBidder(Colleague bidder, int bidAmount);
}
