package com.adidas.lld.cricbuzz.team;

import com.adidas.lld.cricbuzz.innings.BallDetails;
import com.adidas.lld.cricbuzz.innings.OverDetails;
import com.adidas.lld.cricbuzz.team.player.PlayerDetails;

public class Wicket {
  public WicketType wicketType;
  public PlayerDetails takenBy;
  public OverDetails overDetails;
  public BallDetails ballDetails;

  public Wicket(WicketType wicketType, PlayerDetails takenBy, OverDetails overDetails, BallDetails ballDetails){
    this.wicketType = wicketType;
    this.takenBy = takenBy;
    this.overDetails = overDetails;
    this.ballDetails = ballDetails;

  }
}
