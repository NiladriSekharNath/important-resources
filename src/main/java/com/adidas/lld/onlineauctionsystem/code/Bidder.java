package com.adidas.lld.onlineauctionsystem.code;

public class Bidder implements Colleague {
  String name;
  AuctionMediator auctionMediator;
  Bidder(String name, AuctionMediator auctionMediator){
    this.name = name;
    this.auctionMediator = auctionMediator;
  }

  @Override
  public void placeBid(int bidAmount) {
    auctionMediator.placeBidder(this, bidAmount);
  }

  @Override
  public void receiveBidNotification(int bidAmount) {
    System.out.println("Bidder " + name + " get the notification that someone has placed a bid of amount " + bidAmount);
  }

  @Override
  public String getName() {
    return name;
  }
}
