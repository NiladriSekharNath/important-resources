package com.adidas.lld.cricbuzz.scoreupdater;

import com.adidas.lld.cricbuzz.innings.BallDetails;

public interface ScoreUpdaterObserver {
  public void update(BallDetails ballDetails);
}
