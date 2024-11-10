package com.adidas.lld.cricbuzz;

import com.adidas.lld.cricbuzz.MatchType;

public class T20MatchType implements MatchType {
  @Override
  public int noOfOvers() {
    return 20;
  }

  @Override
  public int maxOverCountBowlers() {
    return 5;
  }
}
