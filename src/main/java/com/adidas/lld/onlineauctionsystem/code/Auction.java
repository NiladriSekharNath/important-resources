package com.adidas.lld.onlineauctionsystem.code;

import java.util.ArrayList;
import java.util.List;

public class Auction implements AuctionMediator{
  List<Colleague> colleagues = new ArrayList<>();
  @Override
  public void addBidder(Colleague bidder) {
    colleagues.add(bidder);
  }

  @Override
  public void placeBidder(Colleague bidder, int bidAmount) {
    for(Colleague colleague : colleagues){
      if(!colleague.getName().equalsIgnoreCase(bidder.getName())){
        colleague.receiveBidNotification(bidAmount);
      }
    }
  }
}
